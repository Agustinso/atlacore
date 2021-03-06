package com.plushnode.atlacore.game.ability.air;

import com.plushnode.atlacore.collision.*;
import com.plushnode.atlacore.game.Game;
import com.plushnode.atlacore.game.ability.Ability;
import com.plushnode.atlacore.game.ability.ActivationMethod;
import com.plushnode.atlacore.collision.geometry.Ray;
import com.plushnode.atlacore.collision.geometry.Sphere;
import com.plushnode.atlacore.config.Configurable;
import com.plushnode.atlacore.game.ability.UpdateResult;
import com.plushnode.atlacore.game.ability.common.Burstable;
import com.plushnode.atlacore.game.attribute.Attribute;
import com.plushnode.atlacore.game.attribute.Attributes;
import com.plushnode.atlacore.platform.*;
import com.plushnode.atlacore.platform.block.Block;
import com.plushnode.atlacore.platform.block.BlockSetter;
import com.plushnode.atlacore.platform.block.Material;
import com.plushnode.atlacore.policies.removal.*;
import com.plushnode.atlacore.util.Flight;
import com.plushnode.atlacore.util.VectorUtil;
import com.plushnode.atlacore.util.WorldUtil;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.util.Collection;
import java.util.Collections;

public class AirBlast implements Ability, Burstable {
    private static final double BLOCK_COLLISION_RADIUS = 0.5;
    public static Config config = new Config();

    private User user;
    private Config userConfig;
    private World world;
    private Location origin;
    private Location location;
    private Vector3D direction;
    private boolean launched;
    private boolean selectedOrigin;
    private long nextRenderTime;
    private long renderInterval;
    private int particleCount;
    private CompositeRemovalPolicy removalPolicy;

    @Override
    public boolean activate(User user, ActivationMethod method) {
        for (AirBlast blast : Game.getAbilityInstanceManager().getPlayerInstances(user, AirBlast.class)) {
            if (!blast.launched) {
                if (method == ActivationMethod.Sneak) {
                    blast.selectOrigin();

                    if (!Game.getProtectionSystem().canBuild(user, blast.origin)) {
                        Game.getAbilityInstanceManager().destroyInstance(user, blast);
                    }
                } else {
                    blast.launch();
                }

                return false;
            }
        }

        this.user = user;
        this.userConfig = Game.getAttributeSystem().calculate(this, new Config());
        this.world = user.getWorld();
        this.launched = false;
        this.selectedOrigin = false;
        this.particleCount = userConfig.particles;

        this.removalPolicy = new CompositeRemovalPolicy(getDescription(),
                new IsDeadRemovalPolicy(user),
                new OutOfRangeRemovalPolicy(user, userConfig.selectOutOfRange, () -> origin),
                new SwappedSlotsRemovalPolicy<>(user, AirBlast.class),
                new OutOfWorldRemovalPolicy(user)
        );

        if (method == ActivationMethod.Sneak) {
            selectOrigin();

            if (!Game.getProtectionSystem().canBuild(user, origin)) {
                return false;
            }
        } else {
            this.origin = user.getEyeLocation();

            if (!Game.getProtectionSystem().canBuild(user, origin)) {
                return false;
            }

            launch();
        }

        return true;
    }

    @Override
    public void recalculateConfig() {
        this.userConfig = Game.getAttributeSystem().calculate(this, config);
    }

    private void selectOrigin() {
        Ray ray = new Ray(user.getEyeLocation(), user.getDirection());

        this.origin = RayCaster.cast(user.getWorld(), ray, userConfig.selectRange, true);
        // Move origin back slightly so it doesn't collide with ground.
        this.origin = this.origin.subtract(user.getDirection().scalarMultiply(BLOCK_COLLISION_RADIUS));

        this.selectedOrigin = true;
    }

    private void launch() {
        this.launched = true;
        this.location = origin;

        Location target = RayCaster.cast(user, new Ray(user.getEyeLocation(), user.getDirection()), userConfig.range, true, true);
        this.direction = VectorUtil.normalizeOrElse(target.subtract(origin).toVector(), Vector3D.PLUS_I);

        removalPolicy.removePolicyType(IsDeadRemovalPolicy.class);
        removalPolicy.removePolicyType(OutOfRangeRemovalPolicy.class);
        removalPolicy.removePolicyType(SwappedSlotsRemovalPolicy.class);

        user.setCooldown(this, userConfig.cooldown);
    }

    @Override
    public UpdateResult update() {
        long time = System.currentTimeMillis();

        if (removalPolicy.shouldRemove()) {
            return UpdateResult.Remove;
        }

        if (!launched) {
            Game.plugin.getParticleRenderer().display(ParticleEffect.SPELL,
                    (float)Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random(),
                    userConfig.selectParticles, origin);
        } else {
            Location previous = location;

            location = location.add(direction.scalarMultiply(userConfig.speed));

            if (time > this.nextRenderTime) {
                Game.plugin.getParticleRenderer().display(ParticleEffect.SPELL,
                        0.275f, 0.275f, 0.275f, 0.0f,
                        particleCount, location);
                this.nextRenderTime = time + this.renderInterval;
            }

            if (location.distanceSquared(origin) >= userConfig.range * userConfig.range) {
                return UpdateResult.Remove;
            }

            if (!Game.getProtectionSystem().canBuild(user, location)) {
                return UpdateResult.Remove;
            }

            extinguishFire(location);

            Sphere collider = new Sphere(location.toVector(), userConfig.entityCollisionRadius);

            // Handle user separately from the general entity collision.
            if (this.selectedOrigin) {
                if (user.getBounds().at(user.getLocation()).intersects(collider)) {
                    affect(user);
                }
            }

            CollisionUtil.handleEntityCollisions(user, collider, this::affect, false, false);

            Sphere blockCollider = new Sphere(location.toVector(),BLOCK_COLLISION_RADIUS);
            boolean collision = CollisionUtil.handleBlockCollisions(blockCollider, previous, location, true).getFirst();

            if (collision && doBlockCollision(previous)) {
                return UpdateResult.Remove;
            }
        }

        return UpdateResult.Continue;
    }

    private void extinguishFire(Location location) {
        for (Block block : WorldUtil.getNearbyBlocks(location, userConfig.abilityCollisionRadius)) {
            if (block.getType() == Material.FIRE) {
                if (!Game.getProtectionSystem().canBuild(user, location)) {
                    continue;
                }

                Game.plugin.getBlockSetter(BlockSetter.Flag.FAST).setBlock(block, Material.AIR);
            }
        }
    }

    // Don't do block collision on initial launch of selected area.
    private boolean doBlockCollision(Location previous) {
        if (!this.selectedOrigin) return true;

        return previous.distanceSquared(origin) > BLOCK_COLLISION_RADIUS * BLOCK_COLLISION_RADIUS;
    }

    private boolean affect(Entity entity) {
        double factor = userConfig.otherPush;

        if (entity.equals(user)) {
            factor = userConfig.selfPush;
        }

        factor *= 1.0 - (location.distance(origin) / (2 * userConfig.range));

        // Reduce the push if the player is on the ground.
        if (entity.equals(user) && WorldUtil.isOnGround(entity)) {
            factor *= 0.5;
        }

        Vector3D velocity = entity.getVelocity();
        // The strength of the entity's velocity in the direction of the blast.
        double strength = velocity.dotProduct(direction);

        if (strength > factor) {
            velocity = velocity.scalarMultiply(0.5).add(direction.scalarMultiply(strength / 2));
        } else if (strength > factor * 0.5) {
            velocity = velocity.add(direction.scalarMultiply(factor - strength));
        } else {
            velocity = velocity.add(direction.scalarMultiply(factor * 0.5));
        }

        entity.setVelocity(velocity);
        entity.setFireTicks(0);

        if (entity instanceof User) {
            // Give the target Flight so they don't take fall damage.
            new Flight.GroundRemovalTask((User)entity, 1, 20000);
        }

        return false;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void handleCollision(Collision collision) {
        if (collision.shouldRemoveFirst()) {
            Game.getAbilityInstanceManager().destroyInstance(user, this);
        }
    }

    @Override
    public Collection<Collider> getColliders() {
        if (location == null) {
            return Collections.emptyList();
        }

        return Collections.singletonList(new Sphere(location.toVector(), userConfig.abilityCollisionRadius, world));
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public String getName() {
        return "AirBlast";
    }

    @Override
    // Used to initialize the blast for bursts.
    public void initialize(User user, Location location, Vector3D direction) {
        this.user = user;
        this.userConfig = Game.getAttributeSystem().calculate(this, config);
        this.selectedOrigin = false;
        this.launched = true;
        this.direction = direction;
        this.origin = location;
        this.location = location;

        removalPolicy = new CompositeRemovalPolicy(getDescription());
    }

    @Override
    public void setRenderInterval(long interval) {
        this.renderInterval = interval;
    }

    @Override
    public void setRenderParticleCount(int count) {
        this.particleCount = count;
    }

    public static class Config extends Configurable {
        public boolean enabled;
        @Attribute(Attributes.COOLDOWN)
        public long cooldown;
        @Attribute(Attributes.RANGE)
        public double range;
        @Attribute(Attributes.SPEED)
        public double speed;
        @Attribute(Attributes.ENTITY_COLLISION_RADIUS)
        public double entityCollisionRadius;
        @Attribute(Attributes.ABILITY_COLLISION_RADIUS)
        public double abilityCollisionRadius;
        public int particles;
        @Attribute(Attributes.STRENGTH)
        public double selfPush;
        @Attribute(Attributes.STRENGTH)
        public double otherPush;

        @Attribute(Attributes.SELECTION)
        public double selectRange;
        @Attribute(Attributes.SELECTION)
        public double selectOutOfRange;
        public int selectParticles;

        @Override
        public void onConfigReload() {
            CommentedConfigurationNode abilityNode = config.getNode("abilities", "air", "airblast");

            enabled = abilityNode.getNode("enabled").getBoolean(true);
            cooldown = abilityNode.getNode("cooldown").getLong(500);
            range = abilityNode.getNode("range").getDouble(25.0);
            speed = abilityNode.getNode("speed").getDouble(1.25);
            particles = abilityNode.getNode("particles").getInt(6);
            entityCollisionRadius = abilityNode.getNode("entity-collision-radius").getDouble(2.0);
            abilityCollisionRadius = abilityNode.getNode("ability-collision-radius").getDouble(2.0);

            selfPush = abilityNode.getNode("push").getNode("self").getDouble(2.5);
            otherPush = abilityNode.getNode("push").getNode("other").getDouble(3.5);

            selectRange = abilityNode.getNode("select").getNode("range").getDouble(10.0);
            selectOutOfRange = abilityNode.getNode("select").getNode("out-of-range").getDouble(35.0);
            selectParticles = abilityNode.getNode("select").getNode("particles").getInt(4);

            abilityNode.getNode("description").getString("Test AirBlast description");
            abilityNode.getNode("instructions").getString("Punch to shoot a blast of air that knocks entities back. \nSneak to select an origin.");

            abilityNode.getNode("speed").setComment("How many meters the blast advances with each tick.");
        }
    }
}
