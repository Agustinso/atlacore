package com.plushnode.atlacore.ability.air;

import com.plushnode.atlacore.*;
import com.plushnode.atlacore.ability.Ability;
import com.plushnode.atlacore.ability.AbilityDescription;
import com.plushnode.atlacore.ability.ActivationMethod;
import com.plushnode.atlacore.block.Block;
import com.plushnode.atlacore.block.Material;
import com.plushnode.atlacore.collision.AABB;
import com.plushnode.atlacore.policies.removal.CannotBendRemovalPolicy;
import com.plushnode.atlacore.policies.removal.CompositeRemovalPolicy;
import com.plushnode.atlacore.policies.removal.IsDeadRemovalPolicy;
import com.plushnode.atlacore.policies.removal.IsOfflineRemovalPolicy;
import com.plushnode.atlacore.util.MaterialUtil;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AirScooter implements Ability {
    private Set<Material> groundMaterials = Stream.of(Material.WATER, Material.STATIONARY_WATER, Material.LAVA, Material.STATIONARY_LAVA).collect(Collectors.toSet());

    private long cooldown = 0;
    private double targetHeight = 1.25;
    private double springStiffness = 0.3;
    private double speed = 0.6;
    private int heightTolerance = 10;
    private boolean liquidMovement = true;
    private double liquidClimbSpeed = 0.6;
    private long minDuration = 100;

    private User user;
    private HeightPredictor heightPredictor;
    private DoubleSmoother heightSmoother;
    private CollisionDetector collisionDetector = new RelaxedCollisionDetector();
    private int stuckCount;
    private double verticalPosition;

    private CompositeRemovalPolicy removalPolicy;

    @Override
    public boolean create(User user, ActivationMethod method) {
        if (user instanceof Player) {
            Player player = (Player)user;

            if (player.isSneaking()) {
                return false;
            }
        }

        this.user = user;
        this.heightPredictor = new HeightPredictor(user, targetHeight, speed);
        this.heightSmoother = new DoubleSmoother(heightTolerance);

        double dist = Game.getCollisionSystem().distanceAboveGround(user, groundMaterials);
        // Only create AirScooter is the player is in the air and near the ground.
        if ((dist < 0.5 || dist > 5.0) && !user.getLocation().getBlock().isLiquid()) {
            return false;
        }

        AbilityDescription description = Game.getAbilityRegistry().getAbilityDescription(this);

        this.removalPolicy = new CompositeRemovalPolicy(description,
                new CannotBendRemovalPolicy(user, description, true, false),
                new IsOfflineRemovalPolicy(user),
                new IsDeadRemovalPolicy(user)
        );

        return true;
    }

    @Override
    public boolean update() {
        if (this.removalPolicy.shouldRemove()) {
            return true;
        }

        if (collisionDetector.isColliding(user)) {
            return true;
        }

        double minSpeed = 0.1;

        if (user.getVelocity().getNormSq() < minSpeed * minSpeed) {
            ++stuckCount;
        } else {
            stuckCount = 0;
        }

        if (stuckCount > 10) {
            return true;
        }

        if (!move()) {
            return true;
        }

        render();
        return false;
    }

    @Override
    public void destroy() {

    }

    public void render() {
        double rotationFrequency = 3;

        verticalPosition += (2 * Math.PI) / (20 / rotationFrequency);
        Location base = user.getLocation().clone();

        int horizontalParticles = 10;
        double radius = 0.6;
        for (int i = 0; i < horizontalParticles; ++i) {
            double angle = ((Math.PI * 2) / horizontalParticles) * i;

            double x = radius * Math.cos(angle) * Math.sin(verticalPosition);
            double y = radius * Math.cos(verticalPosition);
            double z = radius * Math.sin(angle) * Math.sin(verticalPosition);

            Game.plugin.getParticleRenderer().display(ParticleEffect.SPELL, 0.0f, 0.0f, 0.0f, 0.0f, 2, base.clone().add(x, y, z), 257);
        }
    }

    @Override
    public String getName() {
        return "AirScooter";
    }

    private boolean move() {
        Location location = user.getEyeLocation().clone();
        location.setPitch(0);
        Vector3D direction = location.getDirection().normalize();


        // How far the player is above the ground.
        double height = Game.getCollisionSystem().distanceAboveGround(user, groundMaterials);
        double maxHeight = targetHeight + 2.0;
        double smoothedHeight = heightSmoother.add(height);

        if (liquidMovement && user.getLocation().getBlock().isLiquid()) {
            height = targetHeight * (1.0 - liquidClimbSpeed);
        } else {
            // Destroy ability if player gets too far from ground.
            if (smoothedHeight > maxHeight) {
                return false;
            }
        }

        double predictedHeight = heightPredictor.getPrediction();

        // Calculate the spring force to push the player back to the target height.
        double displacement = height - predictedHeight;
        double force = -springStiffness * displacement;

        double maxForce = 0.5;
        if (Math.abs(force) > maxForce) {
            // Cap the force to maxForce so the player isn't instantly pulled to the ground.
            force = force / Math.abs(force) * maxForce;
        }

        Vector3D velocity = direction.scalarMultiply(speed);
        // Set y to force.
        velocity = velocity.add(new Vector3D(0, -velocity.getY() + force, 0.0));

        user.setVelocity(velocity);
        user.setFallDistance(0);

        return true;
    }

    private interface CollisionDetector {
        boolean isColliding(User user);
    }

    private abstract class AbstractCollisionDetector implements CollisionDetector {
        protected boolean isCollision(Location location) {
            Block block = location.getBlock();

            return !MaterialUtil.isTransparent(block) || (!liquidMovement && block.isLiquid()) || MaterialUtil.isSolid(block);
        }
    }

    private class RelaxedCollisionDetector extends AbstractCollisionDetector {
        @Override
        public boolean isColliding(User user) {
            // The location in front of the player, where the player will be in one second.
            Location front = user.getEyeLocation().clone().subtract(0.0, 0.5, 0.0);
            front.setPitch(0);

            Vector3D direction = front.getDirection();
            direction = new Vector3D(direction.getX(), 0, direction.getZ()).normalize();

            double playerSpeed = user.getVelocity().subtract(new Vector3D(0, user.getVelocity().getY(), 0)).getNorm();

            front.add(direction.scalarMultiply(Math.max(speed, playerSpeed)));

            return isCollision(front);
        }
    }

    private static class HeightPredictor {
        private static final Vector3D[] DIRECTIONS = {
                new Vector3D(0, 0, 0),
                new Vector3D(1, 0, 0), new Vector3D(-1, 0, 0),
                new Vector3D(0, 1, 0), new Vector3D(0, -1, 0),
                new Vector3D(0, 0, -1), new Vector3D(0, 0, -1)
        };

        private User user;
        private double targetHeight;
        private double speed;

        public HeightPredictor(User user, double targetHeight, double speed) {
            this.user = user;
            this.targetHeight = targetHeight;
            this.speed = speed;
        }

        public double getPrediction() {
            Location location = user.getLocation().clone();
            Vector3D currentDirection = location.getDirection();
            currentDirection = currentDirection.subtract(new Vector3D(0, currentDirection.getY(), 0)).normalize();

            double playerSpeed = user.getVelocity().subtract(new Vector3D(0, user.getVelocity().getY(), 0)).getNorm();

            double s = Math.max(speed, playerSpeed) * 3;
            location.add(currentDirection.scalarMultiply(s));

            AABB playerBounds = Game.getCollisionSystem().getAABB(user).at(location);

            Location checkLocation = location.clone();
            // Project the player forward and check all surrounding blocks for collision.
            for (Vector3D direction : DIRECTIONS) {
                checkLocation.setX(location.getX() + direction.getX());
                checkLocation.setY(location.getY() + direction.getY());
                checkLocation.setZ(location.getZ() + direction.getZ());

                Block block = checkLocation.getBlock();

                AABB bounds = Game.getCollisionSystem().getAABB(block).at(block.getLocation());

                if(bounds.intersects(playerBounds)) {
                    // Player will collide with a block soon, so try to raise the player over it.
                    return targetHeight + 1.0;
                }
            }

            return targetHeight;
        }
    }

    private static class DoubleSmoother {
        private double[] values;
        private int size;
        private int index;

        public DoubleSmoother(int size) {
            this.size = size;
            this.index = 0;

            values = new double[size];
        }

        public double add(double value) {
            values[index] = value;
            index = (index + 1) % size;
            return get();
        }

        public double get() {
            return Arrays.stream(this.values).sum() / this.size;
        }
    }
}