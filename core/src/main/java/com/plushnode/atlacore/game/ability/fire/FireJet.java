package com.plushnode.atlacore.game.ability.fire;

import com.plushnode.atlacore.config.Configurable;
import com.plushnode.atlacore.game.Game;
import com.plushnode.atlacore.game.ability.Ability;
import com.plushnode.atlacore.game.ability.ActivationMethod;
import com.plushnode.atlacore.game.ability.UpdateResult;
import com.plushnode.atlacore.platform.Location;
import com.plushnode.atlacore.platform.ParticleEffect;
import com.plushnode.atlacore.platform.User;
import com.plushnode.atlacore.platform.block.Block;
import com.plushnode.atlacore.platform.block.BlockSetter;
import com.plushnode.atlacore.platform.block.Material;
import com.plushnode.atlacore.util.Flight;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;

public class FireJet implements Ability {
    public static Config config = new Config();

    private User user;
    private Flight flight;
    private long startTime;

    @Override
    public boolean activate(User user, ActivationMethod method) {
        this.user = user;
        this.startTime = System.currentTimeMillis();

        Block block = user.getLocation().getBlock();
        boolean ignitable = IgnitableBlocks.isIgnitable(block);

        if (!ignitable && block.getType() != Material.AIR) {
            return false;
        }

        this.flight = Flight.get(user);
        user.setCooldown(this);

        if (ignitable && Game.getProtectionSystem().canBuild(user, block.getLocation())) {
            // TODO: TempFire
            Game.plugin.getBlockSetter(BlockSetter.Flag.FAST).setBlock(block, Material.FIRE);
        }

        return true;
    }

    @Override
    public UpdateResult update() {
        long time = System.currentTimeMillis();

        if (System.currentTimeMillis() > startTime + config.duration) {
            return UpdateResult.Remove;
        }

        if (user.getLocation().getBlock().isLiquid()) {
            return UpdateResult.Remove;
        }

        // scale down to 0.5 speed near the end
        double factor = 1.0 - ((time - startTime) / (2.0 * config.duration));

        this.user.setVelocity(this.user.getDirection().scalarMultiply(config.speed * factor));
        this.user.setFallDistance(0.0f);

        Location location = user.getLocation();
        Game.plugin.getParticleRenderer().display(ParticleEffect.FLAME, 0.6f, 0.6f, 0.6f, 0.0f, 20, location, 257);
        Game.plugin.getParticleRenderer().display(ParticleEffect.SMOKE, 0.6f, 0.6f, 0.6f, 0.0f, 20, location, 257);

        return UpdateResult.Continue;
    }

    @Override
    public void destroy() {
        flight.release();
    }

    @Override
    public String getName() {
        return "FireJet";
    }

    private static class Config extends Configurable {
        public boolean enabled;
        public long cooldown;
        public double speed;
        private long duration;

        @Override
        public void onConfigReload() {
            CommentedConfigurationNode abilityNode = config.getNode("abilities", "fire", "firejet");

            enabled = abilityNode.getNode("enabled").getBoolean(true);
            cooldown = abilityNode.getNode("cooldown").getLong(7000);
            speed = abilityNode.getNode("speed").getDouble(0.8);
            duration = abilityNode.getNode("duration").getLong(2000);
        }
    }
}