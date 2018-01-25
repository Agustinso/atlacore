package com.plushnode.atlacore.protection;

import com.plushnode.atlacore.platform.Location;
import com.plushnode.atlacore.platform.Player;
import com.plushnode.atlacore.platform.User;
import com.plushnode.atlacore.game.ability.AbilityDescription;
import com.plushnode.atlacore.protection.cache.PerPlayerProtectionCache;
import com.plushnode.atlacore.protection.cache.ProtectionCache;

import java.util.*;

public class ProtectionSystem {
    private Map<String, RegionProtection> worldProtections = new HashMap<>();
    private RegionProtection globalProtection = new RegionProtection();
    private ProtectionFactory protectionFactory = new ProtectionFactory();
    private boolean allowHarmless;
    private ProtectionCache cache = new PerPlayerProtectionCache();

    public ProtectionSystem() {

    }

    public void setCache(ProtectionCache cache) {
        this.cache = cache;
    }

    public boolean canBuild(User user, Location loc) {
        return canBuild(user, null, loc);
    }

    public boolean canBuild(User user, AbilityDescription abilityDescription, Location location) {
        if (!(user instanceof Player)) return true;

        boolean isHarmless = false;

        if (abilityDescription != null) {
            isHarmless = abilityDescription.isHarmless();
        }

        if (isHarmless && allowHarmless) return true;

        Optional<Boolean> result = cache.canBuild(user, abilityDescription, location);

        if (result.isPresent()) {
            return result.get();
        }

        boolean allowed = canBuildPostCache(user, abilityDescription, location);

        cache.store(user, abilityDescription, location, allowed);

        return allowed;
    }

    private boolean canBuildPostCache(User user, AbilityDescription abilityDescription, Location loc) {
        if (loc == user.getLocation()) {
            return canBuildInRegions(user, abilityDescription, loc);
        }

        for (Location location : new Location[] { loc, user.getLocation() }) {
            if (!canBuildInRegions(user, abilityDescription, location)) {
                return false;
            }
        }

        return true;
    }

    private boolean canBuildInRegions(User user, AbilityDescription abilityDescription, Location location) {
        if (!globalProtection.canBuild(user, abilityDescription, location)) {
            return false;
        }

        String worldName = location.getWorld().getName();
        RegionProtection worldProtection = worldProtections.get(worldName);

        return worldProtection == null || worldProtection.canBuild(user, abilityDescription, location);
    }

    // Reset the protection system from config file.
    public void reload() {
        // todo: read from config file

        allowHarmless = true;
        cache.reload();

        globalProtection.clearProtectMethods();
        worldProtections = new HashMap<>();

        RegionProtection worldProtection = new RegionProtection();
        addProtection(worldProtection, Arrays.asList("Towny", "Factions", "WorldGuard", "LWC"));
        worldProtections.put("world", worldProtection);
    }

    private void addProtection(RegionProtection protection, List<String> methodNames) {
        if (methodNames == null) return;

        for (String methodName : methodNames) {
            try {
                ProtectMethod method = this.protectionFactory.getProtectMethod(methodName);

                if (method == null) {

                    System.out.println("Failed to find ProtectMethod " + methodName);
                    continue;
                }

                protection.addProtectMethod(method);
            } catch (PluginNotFoundException e) {
                System.out.println("ProtectMethod " + methodName + " not able to be used since plugin was not found.");
            }
        }
    }

    public ProtectionFactory getFactory() {
        return this.protectionFactory;
    }
}
