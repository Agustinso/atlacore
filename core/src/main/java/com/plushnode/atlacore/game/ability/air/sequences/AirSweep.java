package com.plushnode.atlacore.game.ability.air.sequences;

import com.plushnode.atlacore.collision.Collider;
import com.plushnode.atlacore.collision.Collision;
import com.plushnode.atlacore.config.Configurable;
import com.plushnode.atlacore.game.Game;
import com.plushnode.atlacore.game.ability.Ability;
import com.plushnode.atlacore.game.ability.ActivationMethod;
import com.plushnode.atlacore.game.ability.UpdateResult;
import com.plushnode.atlacore.game.ability.common.ParticleStream;
import com.plushnode.atlacore.game.attribute.Attribute;
import com.plushnode.atlacore.game.attribute.Attributes;
import com.plushnode.atlacore.math.CubicHermiteSpline;
import com.plushnode.atlacore.platform.*;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class AirSweep implements Ability {
    public static Config config = new Config();

    private User user;
    private Config userConfig;
    private long startTime;
    private CubicHermiteSpline spline;
    private List<ParticleStream> streams;
    private List<Entity> affected = new ArrayList<>();
    private int launchCount;
    private boolean linear;
    private Location origin;
    private double potential;

    @Override
    public boolean activate(User user, ActivationMethod method) {
        this.user = user;
        this.userConfig = Game.getAttributeSystem().calculate(this, config);
        this.startTime = System.currentTimeMillis();
        this.spline = new CubicHermiteSpline(0.1);
        this.streams = new ArrayList<>();
        this.linear = "linear".equalsIgnoreCase(userConfig.interpolationMethod);
        this.origin = null;

        user.setCooldown(this, userConfig.cooldown);

        return true;
    }

    @Override
    public void recalculateConfig() {
        this.userConfig = Game.getAttributeSystem().calculate(this, config);
    }

    @Override
    public UpdateResult update() {
        long time = System.currentTimeMillis();

        if (time < startTime + userConfig.sampleTime) {
            // Sample target positions and add them to the spline.
            // These positions get interpolated later.
            Vector3D position = user.getEyeLocation().add(user.getDirection().scalarMultiply(userConfig.range)).toVector();
            spline.addKnot(position);

            return UpdateResult.Continue;
        }

        if (this.origin == null || !userConfig.lockedLaunchOrigin) {
            this.origin = user.getEyeLocation();
        }

        // Clear out any intermediate knots so it becomes a line.
        if (linear && spline.getKnots().size() > 2) {
            List<Vector3D> knots = spline.getKnots();
            Vector3D begin = knots.get(0);
            Vector3D end = knots.get(knots.size() - 1);

            knots.clear();
            spline.addKnot(begin);
            spline.addKnot(end);
        }

        // Launch a few streams per tick to give it a delay.
        if (launchCount < userConfig.streamCount) {
            potential += userConfig.streamCount / (userConfig.launchDuration / 50.0);

            int count = Math.min(userConfig.streamCount - launchCount, (int)potential - launchCount);

            for (int i = 0; i < count; ++i) {
                // Interpolate based on the initial samples gathered.
                Vector3D target = spline.interpolate(launchCount / (double)userConfig.streamCount);
                Vector3D direction = target.subtract(this.origin.toVector()).normalize();

                streams.add(new SweepStream(user, this.origin, direction, userConfig.range, userConfig.speed, 0.5, 0.5, userConfig.damage));
                ++launchCount;
            }
        }

        streams.removeIf(stream -> !stream.update());

        return (streams.isEmpty() && launchCount >= userConfig.streamCount) ? UpdateResult.Remove : UpdateResult.Continue;
    }

    @Override
    public void destroy() {

    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public String getName() {
        return "AirSweep";
    }

    @Override
    public void handleCollision(Collision collision) {
        if (collision.shouldRemoveFirst()) {
            Game.getAbilityInstanceManager().destroyInstance(user, this);
        }
    }

    @Override
    public Collection<Collider> getColliders() {
        return streams.stream()
                .map(ParticleStream::getCollider)
                .collect(Collectors.toList());
    }

    private class SweepStream extends ParticleStream {
        public SweepStream(User user, Location origin, Vector3D direction, double range, double speed, double entityCollisionRadius, double abilityCollisionRadius, double damage) {
            super(user, origin, direction, range, speed, entityCollisionRadius, abilityCollisionRadius, damage);
        }

        @Override
        public void render() {
            Game.plugin.getParticleRenderer().display(ParticleEffect.SPELL, 0.0f, 0.0f, 0.0f, 0.0f, 1, location);
        }

        @Override
        public boolean onEntityHit(Entity entity) {
            if (!Game.getProtectionSystem().canBuild(user, entity.getLocation())) {
                return false;
            }

            if (affected.contains(entity)) {
                return true;
            }

            ((LivingEntity) entity).damage(damage, user);
            entity.setVelocity(direction.scalarMultiply(userConfig.knockback));

            affected.add(entity);

            return true;
        }
    }

    public static class Config extends Configurable {
        public boolean enabled;
        @Attribute(Attributes.COOLDOWN)
        public long cooldown;
        @Attribute(Attributes.RANGE)
        public double range;
        @Attribute(Attributes.SPEED)
        public double speed;
        @Attribute(Attributes.DAMAGE)
        public double damage;
        @Attribute(Attributes.STRENGTH)
        public double knockback;
        public int sampleTime;
        public int streamCount;
        public String interpolationMethod;
        public int launchDuration;
        public boolean lockedLaunchOrigin;

        @Override
        public void onConfigReload() {
            CommentedConfigurationNode abilityNode = config.getNode("abilities", "air", "sequences", "airsweep");

            enabled = abilityNode.getNode("enabled").getBoolean(true);
            cooldown = abilityNode.getNode("cooldown").getLong(6000);
            range = abilityNode.getNode("range").getDouble(14.0);
            speed = abilityNode.getNode("speed").getDouble(1.4);
            damage = abilityNode.getNode("damage").getDouble(3.0);
            knockback = abilityNode.getNode("knockback").getDouble(3.5);
            sampleTime = abilityNode.getNode("sample-time").getInt(400);
            streamCount = abilityNode.getNode("stream-count").getInt(30);
            interpolationMethod = abilityNode.getNode("interpolation-method").getString("spline");
            launchDuration = abilityNode.getNode("launch").getNode("duration").getInt(500);
            lockedLaunchOrigin = abilityNode.getNode("launch").getNode("locked-origin").getBoolean(true);

            abilityNode.getNode("interpolation-method").setComment("This sets the method in which the sweep interpolates the samples.\nThis can be set to either 'linear' or 'spline'.");
        }
    }
}
