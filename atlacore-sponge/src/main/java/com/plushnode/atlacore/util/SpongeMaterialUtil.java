package com.plushnode.atlacore.util;

import com.plushnode.atlacore.platform.block.Material;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class SpongeMaterialUtil {
    private static Map<Material, BlockType> blockTypes = new HashMap<>();

    private SpongeMaterialUtil() {
    }

    static {
        // Regex find/replace for creating this.
        // (?<Type>.+)\(.+\),
        // blockTypes.put\(Material.$+{Type}, BlockTypes.$+{Type}\);

        blockTypes.put(Material.ACACIA_BUTTON, BlockTypes.WOODEN_BUTTON);
        blockTypes.put(Material.ACACIA_DOOR, BlockTypes.ACACIA_DOOR);
        blockTypes.put(Material.ACACIA_FENCE, BlockTypes.ACACIA_FENCE);
        blockTypes.put(Material.ACACIA_FENCE_GATE, BlockTypes.ACACIA_FENCE_GATE);
        blockTypes.put(Material.ACACIA_LEAVES, BlockTypes.LEAVES);
        blockTypes.put(Material.ACACIA_LOG, BlockTypes.LOG2);
        blockTypes.put(Material.ACACIA_PLANKS, BlockTypes.PLANKS);
        blockTypes.put(Material.ACACIA_PRESSURE_PLATE, BlockTypes.WOODEN_PRESSURE_PLATE);
        blockTypes.put(Material.ACACIA_SAPLING, BlockTypes.SAPLING);
        blockTypes.put(Material.ACACIA_SLAB, BlockTypes.WOODEN_SLAB);
        blockTypes.put(Material.ACACIA_STAIRS, BlockTypes.ACACIA_STAIRS);
        blockTypes.put(Material.ACACIA_TRAPDOOR, BlockTypes.TRAPDOOR);
        blockTypes.put(Material.ACACIA_WOOD, BlockTypes.LOG2);
        blockTypes.put(Material.ACTIVATOR_RAIL, BlockTypes.ACTIVATOR_RAIL);
        blockTypes.put(Material.AIR, BlockTypes.AIR);
        blockTypes.put(Material.ALLIUM, BlockTypes.RED_FLOWER);
        blockTypes.put(Material.ANDESITE, BlockTypes.STONE);
        blockTypes.put(Material.ANVIL, BlockTypes.ANVIL);
        blockTypes.put(Material.ATTACHED_MELON_STEM, BlockTypes.MELON_STEM);
        blockTypes.put(Material.ATTACHED_PUMPKIN_STEM, BlockTypes.PUMPKIN_STEM);
        blockTypes.put(Material.AZURE_BLUET, BlockTypes.RED_FLOWER);
        blockTypes.put(Material.BARRIER, BlockTypes.BARRIER);
        blockTypes.put(Material.BEACON, BlockTypes.BEACON);
        blockTypes.put(Material.BEDROCK, BlockTypes.BEDROCK);
        blockTypes.put(Material.BEETROOT, BlockTypes.BEETROOTS);
        blockTypes.put(Material.BEETROOTS, BlockTypes.BEETROOTS);
        blockTypes.put(Material.BIRCH_BUTTON, BlockTypes.WOODEN_BUTTON);
        blockTypes.put(Material.BIRCH_DOOR, BlockTypes.BIRCH_DOOR);
        blockTypes.put(Material.BIRCH_FENCE, BlockTypes.BIRCH_FENCE);
        blockTypes.put(Material.BIRCH_FENCE_GATE, BlockTypes.BIRCH_FENCE_GATE);
        blockTypes.put(Material.BIRCH_LEAVES, BlockTypes.LEAVES);
        blockTypes.put(Material.BIRCH_LOG, BlockTypes.LOG);
        blockTypes.put(Material.BIRCH_PLANKS, BlockTypes.PLANKS);
        blockTypes.put(Material.BIRCH_PRESSURE_PLATE, BlockTypes.WOODEN_PRESSURE_PLATE);
        blockTypes.put(Material.BIRCH_SAPLING, BlockTypes.SAPLING);
        blockTypes.put(Material.BIRCH_SLAB, BlockTypes.WOODEN_SLAB);
        blockTypes.put(Material.BIRCH_STAIRS, BlockTypes.BIRCH_STAIRS);
        blockTypes.put(Material.BIRCH_TRAPDOOR, BlockTypes.TRAPDOOR);
        blockTypes.put(Material.BIRCH_WOOD, BlockTypes.LOG);
        blockTypes.put(Material.BLACK_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.BLACK_BED, BlockTypes.BED);
        blockTypes.put(Material.BLACK_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.BLACK_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.BLACK_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.BLACK_GLAZED_TERRACOTTA, BlockTypes.BLACK_GLAZED_TERRACOTTA);
        blockTypes.put(Material.BLACK_SHULKER_BOX, BlockTypes.BLACK_SHULKER_BOX);
        blockTypes.put(Material.BLACK_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.BLACK_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.BLACK_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.BLACK_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.BLACK_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.BLUE_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.BLUE_BED, BlockTypes.BED);
        blockTypes.put(Material.BLUE_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.BLUE_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.BLUE_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.BLUE_GLAZED_TERRACOTTA, BlockTypes.BLUE_GLAZED_TERRACOTTA);
        blockTypes.put(Material.BLUE_ICE, BlockTypes.ICE);
        blockTypes.put(Material.BLUE_ORCHID, BlockTypes.RED_FLOWER);
        blockTypes.put(Material.BLUE_SHULKER_BOX, BlockTypes.BLUE_SHULKER_BOX);
        blockTypes.put(Material.BLUE_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.BLUE_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.BLUE_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.BLUE_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.BLUE_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.BONE_BLOCK, BlockTypes.BONE_BLOCK);
        blockTypes.put(Material.BOOKSHELF, BlockTypes.BOOKSHELF);
        blockTypes.put(Material.BREWING_STAND, BlockTypes.BREWING_STAND);
        blockTypes.put(Material.BRICK, BlockTypes.BRICK_BLOCK);
        blockTypes.put(Material.BRICKS, BlockTypes.BRICK_BLOCK);
        blockTypes.put(Material.BRICK_SLAB, BlockTypes.STONE_SLAB);
        blockTypes.put(Material.BRICK_STAIRS, BlockTypes.BRICK_STAIRS);
        blockTypes.put(Material.BROWN_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.BROWN_BED, BlockTypes.BED);
        blockTypes.put(Material.BROWN_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.BROWN_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.BROWN_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.BROWN_GLAZED_TERRACOTTA, BlockTypes.BROWN_GLAZED_TERRACOTTA);
        blockTypes.put(Material.BROWN_MUSHROOM, BlockTypes.BROWN_MUSHROOM);
        blockTypes.put(Material.BROWN_MUSHROOM_BLOCK, BlockTypes.BROWN_MUSHROOM_BLOCK);
        blockTypes.put(Material.BROWN_SHULKER_BOX, BlockTypes.BROWN_SHULKER_BOX);
        blockTypes.put(Material.BROWN_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.BROWN_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.BROWN_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.BROWN_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.BROWN_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.CACTUS, BlockTypes.CACTUS);
        blockTypes.put(Material.CAKE, BlockTypes.CAKE);
        blockTypes.put(Material.CARROTS, BlockTypes.CARROTS);
        blockTypes.put(Material.CARVED_PUMPKIN, BlockTypes.LIT_PUMPKIN);
        blockTypes.put(Material.CAULDRON, BlockTypes.CAULDRON);
        blockTypes.put(Material.CHAIN_COMMAND_BLOCK, BlockTypes.CHAIN_COMMAND_BLOCK);
        blockTypes.put(Material.CHEST, BlockTypes.CHEST);
        blockTypes.put(Material.CHIPPED_ANVIL, BlockTypes.ANVIL);
        blockTypes.put(Material.CHISELED_QUARTZ_BLOCK, BlockTypes.QUARTZ_BLOCK);
        blockTypes.put(Material.CHISELED_RED_SANDSTONE, BlockTypes.RED_SANDSTONE);
        blockTypes.put(Material.CHISELED_SANDSTONE, BlockTypes.SANDSTONE);
        blockTypes.put(Material.CHISELED_STONE_BRICKS, BlockTypes.STONEBRICK);
        blockTypes.put(Material.CHORUS_FLOWER, BlockTypes.CHORUS_FLOWER);
        blockTypes.put(Material.CHORUS_PLANT, BlockTypes.CHORUS_PLANT);
        blockTypes.put(Material.CLAY, BlockTypes.CLAY);
        blockTypes.put(Material.COAL_BLOCK, BlockTypes.COAL_BLOCK);
        blockTypes.put(Material.COAL_ORE, BlockTypes.COAL_ORE);
        blockTypes.put(Material.COARSE_DIRT, BlockTypes.DIRT);
        blockTypes.put(Material.COBBLESTONE, BlockTypes.COBBLESTONE);
        blockTypes.put(Material.COBBLESTONE_SLAB, BlockTypes.STONE_SLAB);
        blockTypes.put(Material.COBBLESTONE_STAIRS, BlockTypes.STONE_STAIRS);
        blockTypes.put(Material.COBBLESTONE_WALL, BlockTypes.COBBLESTONE_WALL);
        blockTypes.put(Material.COBWEB, BlockTypes.WEB);
        blockTypes.put(Material.COCOA, BlockTypes.COCOA);
        blockTypes.put(Material.COMMAND_BLOCK, BlockTypes.COMMAND_BLOCK);
        blockTypes.put(Material.COMPARATOR, BlockTypes.UNPOWERED_COMPARATOR);
        blockTypes.put(Material.CRAFTING_TABLE, BlockTypes.CRAFTING_TABLE);
        blockTypes.put(Material.CUT_RED_SANDSTONE, BlockTypes.RED_SANDSTONE);
        blockTypes.put(Material.CUT_SANDSTONE, BlockTypes.SANDSTONE);
        blockTypes.put(Material.CYAN_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.CYAN_BED, BlockTypes.BED);
        blockTypes.put(Material.CYAN_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.CYAN_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.CYAN_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.CYAN_GLAZED_TERRACOTTA, BlockTypes.CYAN_GLAZED_TERRACOTTA);
        blockTypes.put(Material.CYAN_SHULKER_BOX, BlockTypes.CYAN_SHULKER_BOX);
        blockTypes.put(Material.CYAN_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.CYAN_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.CYAN_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.CYAN_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.CYAN_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.DAMAGED_ANVIL, BlockTypes.ANVIL);
        blockTypes.put(Material.DANDELION, BlockTypes.YELLOW_FLOWER);
        blockTypes.put(Material.DARK_OAK_BUTTON, BlockTypes.WOODEN_BUTTON);
        blockTypes.put(Material.DARK_OAK_DOOR, BlockTypes.DARK_OAK_DOOR);
        blockTypes.put(Material.DARK_OAK_FENCE, BlockTypes.DARK_OAK_FENCE);
        blockTypes.put(Material.DARK_OAK_FENCE_GATE, BlockTypes.DARK_OAK_FENCE_GATE);
        blockTypes.put(Material.DARK_OAK_LEAVES, BlockTypes.LEAVES);
        blockTypes.put(Material.DARK_OAK_LOG, BlockTypes.LOG2);
        blockTypes.put(Material.DARK_OAK_PLANKS, BlockTypes.PLANKS);
        blockTypes.put(Material.DARK_OAK_PRESSURE_PLATE, BlockTypes.WOODEN_PRESSURE_PLATE);
        blockTypes.put(Material.DARK_OAK_SAPLING, BlockTypes.SAPLING);
        blockTypes.put(Material.DARK_OAK_SLAB, BlockTypes.WOODEN_SLAB);
        blockTypes.put(Material.DARK_OAK_STAIRS, BlockTypes.DARK_OAK_STAIRS);
        blockTypes.put(Material.DARK_OAK_TRAPDOOR, BlockTypes.TRAPDOOR);
        blockTypes.put(Material.DARK_OAK_WOOD, BlockTypes.LOG2);
        blockTypes.put(Material.DARK_PRISMARINE, BlockTypes.PRISMARINE);
        blockTypes.put(Material.DAYLIGHT_DETECTOR, BlockTypes.DAYLIGHT_DETECTOR);
        blockTypes.put(Material.DEAD_BUSH, BlockTypes.DEADBUSH);
        blockTypes.put(Material.DETECTOR_RAIL, BlockTypes.DETECTOR_RAIL);
        blockTypes.put(Material.DIAMOND_BLOCK, BlockTypes.DIAMOND_BLOCK);
        blockTypes.put(Material.DIAMOND_ORE, BlockTypes.DIAMOND_ORE);
        blockTypes.put(Material.DIORITE, BlockTypes.STONE);
        blockTypes.put(Material.DIRT, BlockTypes.DIRT);
        blockTypes.put(Material.DISPENSER, BlockTypes.DISPENSER);
        blockTypes.put(Material.DRAGON_EGG, BlockTypes.DRAGON_EGG);
        blockTypes.put(Material.DRAGON_HEAD, BlockTypes.SKULL);
        blockTypes.put(Material.DROPPER, BlockTypes.DROPPER);
        blockTypes.put(Material.EMERALD_BLOCK, BlockTypes.EMERALD_BLOCK);
        blockTypes.put(Material.EMERALD_ORE, BlockTypes.EMERALD_ORE);
        blockTypes.put(Material.ENCHANTING_TABLE, BlockTypes.ENCHANTING_TABLE);
        blockTypes.put(Material.ENDER_CHEST, BlockTypes.ENDER_CHEST);
        blockTypes.put(Material.END_GATEWAY, BlockTypes.END_GATEWAY);
        blockTypes.put(Material.END_PORTAL, BlockTypes.END_PORTAL);
        blockTypes.put(Material.END_PORTAL_FRAME, BlockTypes.END_PORTAL_FRAME);
        blockTypes.put(Material.END_ROD, BlockTypes.END_ROD);
        blockTypes.put(Material.END_STONE, BlockTypes.END_STONE);
        blockTypes.put(Material.END_STONE_BRICKS, BlockTypes.END_BRICKS);
        blockTypes.put(Material.FARMLAND, BlockTypes.FARMLAND);
        blockTypes.put(Material.FERN, BlockTypes.TALLGRASS);
        blockTypes.put(Material.FIRE, BlockTypes.FIRE);
        blockTypes.put(Material.FLOWER_POT, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.FROSTED_ICE, BlockTypes.FROSTED_ICE);
        blockTypes.put(Material.FURNACE, BlockTypes.FURNACE);
        blockTypes.put(Material.GLASS, BlockTypes.GLASS);
        blockTypes.put(Material.GLASS_PANE, BlockTypes.GLASS_PANE);
        blockTypes.put(Material.GLOWSTONE, BlockTypes.GLOWSTONE);
        blockTypes.put(Material.GOLD_BLOCK, BlockTypes.GOLD_BLOCK);
        blockTypes.put(Material.GOLD_ORE, BlockTypes.GOLD_ORE);
        blockTypes.put(Material.GRANITE, BlockTypes.STONE);
        blockTypes.put(Material.GRASS, BlockTypes.TALLGRASS);
        blockTypes.put(Material.GRASS_BLOCK, BlockTypes.GRASS);
        blockTypes.put(Material.GRASS_PATH, BlockTypes.GRASS_PATH);
        blockTypes.put(Material.GRAVEL, BlockTypes.GRAVEL);
        blockTypes.put(Material.GRAY_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.GRAY_BED, BlockTypes.BED);
        blockTypes.put(Material.GRAY_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.GRAY_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.GRAY_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.GRAY_GLAZED_TERRACOTTA, BlockTypes.GRAY_GLAZED_TERRACOTTA);
        blockTypes.put(Material.GRAY_SHULKER_BOX, BlockTypes.GRAY_SHULKER_BOX);
        blockTypes.put(Material.GRAY_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.GRAY_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.GRAY_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.GRAY_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.GRAY_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.GREEN_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.GREEN_BED, BlockTypes.BED);
        blockTypes.put(Material.GREEN_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.GREEN_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.GREEN_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.GREEN_GLAZED_TERRACOTTA, BlockTypes.GREEN_GLAZED_TERRACOTTA);
        blockTypes.put(Material.GREEN_SHULKER_BOX, BlockTypes.GREEN_SHULKER_BOX);
        blockTypes.put(Material.GREEN_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.GREEN_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.GREEN_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.GREEN_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.GREEN_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.HAY_BLOCK, BlockTypes.HAY_BLOCK);
        blockTypes.put(Material.HEAVY_WEIGHTED_PRESSURE_PLATE, BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE);
        blockTypes.put(Material.HOPPER, BlockTypes.HOPPER);
        blockTypes.put(Material.ICE, BlockTypes.ICE);
        blockTypes.put(Material.IRON_BARS, BlockTypes.IRON_BARS);
        blockTypes.put(Material.IRON_BLOCK, BlockTypes.IRON_BLOCK);
        blockTypes.put(Material.IRON_DOOR, BlockTypes.IRON_DOOR);
        blockTypes.put(Material.IRON_ORE, BlockTypes.IRON_ORE);
        blockTypes.put(Material.IRON_TRAPDOOR, BlockTypes.IRON_TRAPDOOR);
        blockTypes.put(Material.JACK_O_LANTERN, BlockTypes.LIT_PUMPKIN);
        blockTypes.put(Material.JUKEBOX, BlockTypes.JUKEBOX);
        blockTypes.put(Material.JUNGLE_BUTTON, BlockTypes.WOODEN_BUTTON);
        blockTypes.put(Material.JUNGLE_DOOR, BlockTypes.JUNGLE_DOOR);
        blockTypes.put(Material.JUNGLE_FENCE, BlockTypes.JUNGLE_FENCE);
        blockTypes.put(Material.JUNGLE_FENCE_GATE, BlockTypes.JUNGLE_FENCE_GATE);
        blockTypes.put(Material.JUNGLE_LEAVES, BlockTypes.LEAVES);
        blockTypes.put(Material.JUNGLE_LOG, BlockTypes.LOG);
        blockTypes.put(Material.JUNGLE_PLANKS, BlockTypes.PLANKS);
        blockTypes.put(Material.JUNGLE_PRESSURE_PLATE, BlockTypes.WOODEN_PRESSURE_PLATE);
        blockTypes.put(Material.JUNGLE_SAPLING, BlockTypes.SAPLING);
        blockTypes.put(Material.JUNGLE_SLAB, BlockTypes.WOODEN_SLAB);
        blockTypes.put(Material.JUNGLE_STAIRS, BlockTypes.JUNGLE_STAIRS);
        blockTypes.put(Material.JUNGLE_TRAPDOOR, BlockTypes.TRAPDOOR);
        blockTypes.put(Material.JUNGLE_WOOD, BlockTypes.LOG);
        blockTypes.put(Material.LADDER, BlockTypes.LADDER);
        blockTypes.put(Material.LAPIS_BLOCK, BlockTypes.LAPIS_BLOCK);
        blockTypes.put(Material.LAPIS_ORE, BlockTypes.LAPIS_ORE);
        blockTypes.put(Material.LARGE_FERN, BlockTypes.DOUBLE_PLANT);
        blockTypes.put(Material.LAVA, BlockTypes.LAVA);
        blockTypes.put(Material.LEVER, BlockTypes.LEVER);
        blockTypes.put(Material.LIGHT_BLUE_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.LIGHT_BLUE_BED, BlockTypes.BED);
        blockTypes.put(Material.LIGHT_BLUE_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.LIGHT_BLUE_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.LIGHT_BLUE_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA);
        blockTypes.put(Material.LIGHT_BLUE_SHULKER_BOX, BlockTypes.LIGHT_BLUE_SHULKER_BOX);
        blockTypes.put(Material.LIGHT_BLUE_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.LIGHT_BLUE_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.LIGHT_BLUE_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.LIGHT_BLUE_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.LIGHT_BLUE_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.LIGHT_GRAY_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.LIGHT_GRAY_BED, BlockTypes.BED);
        blockTypes.put(Material.LIGHT_GRAY_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.LIGHT_GRAY_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.LIGHT_GRAY_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.LIGHT_GRAY_GLAZED_TERRACOTTA, BlockTypes.SILVER_GLAZED_TERRACOTTA);
        blockTypes.put(Material.LIGHT_GRAY_SHULKER_BOX, BlockTypes.SILVER_SHULKER_BOX);
        blockTypes.put(Material.LIGHT_GRAY_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.LIGHT_GRAY_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.LIGHT_GRAY_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.LIGHT_GRAY_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.LIGHT_GRAY_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.LIGHT_WEIGHTED_PRESSURE_PLATE, BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE);
        blockTypes.put(Material.LILAC, BlockTypes.DOUBLE_PLANT);
        blockTypes.put(Material.LILY_PAD, BlockTypes.WATERLILY);
        blockTypes.put(Material.LIME_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.LIME_BED, BlockTypes.BED);
        blockTypes.put(Material.LIME_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.LIME_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.LIME_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.LIME_GLAZED_TERRACOTTA, BlockTypes.LIME_GLAZED_TERRACOTTA);
        blockTypes.put(Material.LIME_SHULKER_BOX, BlockTypes.LIME_SHULKER_BOX);
        blockTypes.put(Material.LIME_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.LIME_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.LIME_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.LIME_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.LIME_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.MAGENTA_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.MAGENTA_BED, BlockTypes.BED);
        blockTypes.put(Material.MAGENTA_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.MAGENTA_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.MAGENTA_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.MAGENTA_GLAZED_TERRACOTTA, BlockTypes.MAGENTA_GLAZED_TERRACOTTA);
        blockTypes.put(Material.MAGENTA_SHULKER_BOX, BlockTypes.MAGENTA_SHULKER_BOX);
        blockTypes.put(Material.MAGENTA_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.MAGENTA_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.MAGENTA_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.MAGENTA_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.MAGENTA_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.MAGMA_BLOCK, BlockTypes.MAGMA);
        blockTypes.put(Material.MELON, BlockTypes.MELON_BLOCK);
        blockTypes.put(Material.MELON_STEM, BlockTypes.MELON_STEM);
        blockTypes.put(Material.MOSSY_COBBLESTONE, BlockTypes.MOSSY_COBBLESTONE);
        blockTypes.put(Material.MOSSY_STONE_BRICKS, BlockTypes.STONEBRICK);
        blockTypes.put(Material.MOVING_PISTON, BlockTypes.PISTON_EXTENSION);
        blockTypes.put(Material.MYCELIUM, BlockTypes.MYCELIUM);
        blockTypes.put(Material.NETHERRACK, BlockTypes.NETHERRACK);
        blockTypes.put(Material.NETHER_BRICK, BlockTypes.NETHER_BRICK);
        blockTypes.put(Material.NETHER_BRICKS, BlockTypes.NETHER_BRICK);
        blockTypes.put(Material.NETHER_BRICK_FENCE, BlockTypes.NETHER_BRICK_FENCE);
        blockTypes.put(Material.NETHER_BRICK_SLAB, BlockTypes.WOODEN_SLAB);
        blockTypes.put(Material.NETHER_BRICK_STAIRS, BlockTypes.NETHER_BRICK_STAIRS);
        blockTypes.put(Material.NETHER_PORTAL, BlockTypes.PORTAL);
        blockTypes.put(Material.NETHER_QUARTZ_ORE, BlockTypes.QUARTZ_ORE);
        blockTypes.put(Material.NETHER_WART, BlockTypes.NETHER_WART);
        blockTypes.put(Material.NETHER_WART_BLOCK, BlockTypes.NETHER_WART_BLOCK);
        blockTypes.put(Material.NOTE_BLOCK, BlockTypes.NOTEBLOCK);
        blockTypes.put(Material.OAK_BUTTON, BlockTypes.WOODEN_BUTTON);
        blockTypes.put(Material.OAK_DOOR, BlockTypes.WOODEN_DOOR);
        blockTypes.put(Material.OAK_FENCE, BlockTypes.FENCE);
        blockTypes.put(Material.OAK_FENCE_GATE, BlockTypes.FENCE_GATE);
        blockTypes.put(Material.OAK_LEAVES, BlockTypes.LEAVES);
        blockTypes.put(Material.OAK_LOG, BlockTypes.LOG);
        blockTypes.put(Material.OAK_PLANKS, BlockTypes.PLANKS);
        blockTypes.put(Material.OAK_PRESSURE_PLATE, BlockTypes.WOODEN_PRESSURE_PLATE);
        blockTypes.put(Material.OAK_SAPLING, BlockTypes.SAPLING);
        blockTypes.put(Material.OAK_SLAB, BlockTypes.WOODEN_SLAB);
        blockTypes.put(Material.OAK_STAIRS, BlockTypes.OAK_STAIRS);
        blockTypes.put(Material.OAK_TRAPDOOR, BlockTypes.TRAPDOOR);
        blockTypes.put(Material.OAK_WOOD, BlockTypes.LOG);
        blockTypes.put(Material.OBSERVER, BlockTypes.OBSERVER);
        blockTypes.put(Material.OBSIDIAN, BlockTypes.OBSIDIAN);
        blockTypes.put(Material.ORANGE_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.ORANGE_BED, BlockTypes.BED);
        blockTypes.put(Material.ORANGE_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.ORANGE_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.ORANGE_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.ORANGE_GLAZED_TERRACOTTA, BlockTypes.YELLOW_GLAZED_TERRACOTTA);
        blockTypes.put(Material.ORANGE_SHULKER_BOX, BlockTypes.ORANGE_SHULKER_BOX);
        blockTypes.put(Material.ORANGE_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.ORANGE_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.ORANGE_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.ORANGE_TULIP, BlockTypes.RED_FLOWER);
        blockTypes.put(Material.ORANGE_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.ORANGE_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.OXEYE_DAISY, BlockTypes.RED_FLOWER);
        blockTypes.put(Material.PACKED_ICE, BlockTypes.PACKED_ICE);
        blockTypes.put(Material.PEONY, BlockTypes.DOUBLE_PLANT);
        blockTypes.put(Material.PINK_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.PINK_BED, BlockTypes.BED);
        blockTypes.put(Material.PINK_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.PINK_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.PINK_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.PINK_GLAZED_TERRACOTTA, BlockTypes.PINK_GLAZED_TERRACOTTA);
        blockTypes.put(Material.PINK_SHULKER_BOX, BlockTypes.PINK_SHULKER_BOX);
        blockTypes.put(Material.PINK_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.PINK_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.PINK_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.PINK_TULIP, BlockTypes.RED_FLOWER);
        blockTypes.put(Material.PINK_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.PINK_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.PISTON, BlockTypes.PISTON);
        blockTypes.put(Material.PISTON_HEAD, BlockTypes.PISTON_HEAD);
        blockTypes.put(Material.PLAYER_HEAD, BlockTypes.SKULL);
        blockTypes.put(Material.PLAYER_WALL_HEAD, BlockTypes.SKULL);
        blockTypes.put(Material.PODZOL, BlockTypes.MYCELIUM);
        blockTypes.put(Material.POLISHED_ANDESITE, BlockTypes.STONE);
        blockTypes.put(Material.POLISHED_DIORITE, BlockTypes.STONE);
        blockTypes.put(Material.POLISHED_GRANITE, BlockTypes.STONE);
        blockTypes.put(Material.POPPY, BlockTypes.RED_FLOWER);
        blockTypes.put(Material.POTATOES, BlockTypes.POTATOES);
        blockTypes.put(Material.POTTED_ACACIA_SAPLING, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_ALLIUM, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_AZURE_BLUET, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_BIRCH_SAPLING, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_BLUE_ORCHID, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_BROWN_MUSHROOM, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_CACTUS, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_DANDELION, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_DARK_OAK_SAPLING, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_DEAD_BUSH, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_FERN, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_JUNGLE_SAPLING, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_OAK_SAPLING, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_ORANGE_TULIP, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_OXEYE_DAISY, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_PINK_TULIP, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_POPPY, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_RED_MUSHROOM, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_RED_TULIP, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_SPRUCE_SAPLING, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POTTED_WHITE_TULIP, BlockTypes.FLOWER_POT);
        blockTypes.put(Material.POWERED_RAIL, BlockTypes.GOLDEN_RAIL);
        blockTypes.put(Material.PRISMARINE, BlockTypes.PRISMARINE);
        blockTypes.put(Material.PRISMARINE_BRICKS, BlockTypes.PRISMARINE);
        blockTypes.put(Material.PUMPKIN, BlockTypes.PUMPKIN);
        blockTypes.put(Material.PUMPKIN_STEM, BlockTypes.PUMPKIN_STEM);
        blockTypes.put(Material.PURPLE_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.PURPLE_BED, BlockTypes.BED);
        blockTypes.put(Material.PURPLE_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.PURPLE_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.PURPLE_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.PURPLE_GLAZED_TERRACOTTA, BlockTypes.PURPLE_GLAZED_TERRACOTTA);
        blockTypes.put(Material.PURPLE_SHULKER_BOX, BlockTypes.PURPLE_SHULKER_BOX);
        blockTypes.put(Material.PURPLE_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.PURPLE_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.PURPLE_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.PURPLE_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.PURPLE_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.PURPUR_BLOCK, BlockTypes.PURPUR_BLOCK);
        blockTypes.put(Material.PURPUR_PILLAR, BlockTypes.PURPUR_PILLAR);
        blockTypes.put(Material.PURPUR_SLAB, BlockTypes.PURPUR_SLAB);
        blockTypes.put(Material.PURPUR_STAIRS, BlockTypes.PURPUR_STAIRS);
        blockTypes.put(Material.QUARTZ_BLOCK, BlockTypes.QUARTZ_BLOCK);
        blockTypes.put(Material.QUARTZ_PILLAR, BlockTypes.QUARTZ_BLOCK);
        blockTypes.put(Material.QUARTZ_SLAB, BlockTypes.STONE_SLAB);
        blockTypes.put(Material.QUARTZ_STAIRS, BlockTypes.QUARTZ_STAIRS);
        blockTypes.put(Material.RAIL, BlockTypes.RAIL);
        blockTypes.put(Material.REDSTONE, BlockTypes.REDSTONE_WIRE);
        blockTypes.put(Material.REDSTONE_BLOCK, BlockTypes.REDSTONE_BLOCK);
        blockTypes.put(Material.REDSTONE_LAMP, BlockTypes.REDSTONE_LAMP);
        blockTypes.put(Material.REDSTONE_ORE, BlockTypes.REDSTONE_ORE);
        blockTypes.put(Material.REDSTONE_TORCH, BlockTypes.REDSTONE_TORCH);
        blockTypes.put(Material.REDSTONE_WALL_TORCH, BlockTypes.REDSTONE_TORCH);
        blockTypes.put(Material.REDSTONE_WIRE, BlockTypes.REDSTONE_WIRE);
        blockTypes.put(Material.RED_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.RED_BED, BlockTypes.BED);
        blockTypes.put(Material.RED_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.RED_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.RED_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.RED_GLAZED_TERRACOTTA, BlockTypes.RED_GLAZED_TERRACOTTA);
        blockTypes.put(Material.RED_MUSHROOM, BlockTypes.RED_MUSHROOM);
        blockTypes.put(Material.RED_MUSHROOM_BLOCK, BlockTypes.RED_MUSHROOM_BLOCK);
        blockTypes.put(Material.RED_NETHER_BRICKS, BlockTypes.NETHER_BRICK);
        blockTypes.put(Material.RED_SAND, BlockTypes.SAND);
        blockTypes.put(Material.RED_SANDSTONE, BlockTypes.RED_SANDSTONE);
        blockTypes.put(Material.RED_SANDSTONE_SLAB, BlockTypes.STONE_SLAB);
        blockTypes.put(Material.RED_SANDSTONE_STAIRS, BlockTypes.RED_SANDSTONE_STAIRS);
        blockTypes.put(Material.RED_SHULKER_BOX, BlockTypes.RED_SHULKER_BOX);
        blockTypes.put(Material.RED_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.RED_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.RED_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.RED_TULIP, BlockTypes.RED_FLOWER);
        blockTypes.put(Material.RED_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.RED_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.REPEATER, BlockTypes.UNPOWERED_REPEATER);
        blockTypes.put(Material.REPEATING_COMMAND_BLOCK, BlockTypes.REPEATING_COMMAND_BLOCK);
        blockTypes.put(Material.ROSE_BUSH, BlockTypes.DOUBLE_PLANT);
        blockTypes.put(Material.SAND, BlockTypes.SAND);
        blockTypes.put(Material.SANDSTONE, BlockTypes.SANDSTONE);
        blockTypes.put(Material.SANDSTONE_SLAB, BlockTypes.STONE_SLAB);
        blockTypes.put(Material.SANDSTONE_STAIRS, BlockTypes.SANDSTONE_STAIRS);
        blockTypes.put(Material.SEA_LANTERN, BlockTypes.SEA_LANTERN);
        blockTypes.put(Material.SHULKER_BOX, BlockTypes.YELLOW_SHULKER_BOX);
        blockTypes.put(Material.SIGN, BlockTypes.STANDING_SIGN);
        blockTypes.put(Material.SKELETON_SKULL, BlockTypes.SKULL);
        blockTypes.put(Material.SLIME_BLOCK, BlockTypes.SLIME);
        blockTypes.put(Material.SMOOTH_QUARTZ, BlockTypes.QUARTZ_BLOCK);
        blockTypes.put(Material.SMOOTH_RED_SANDSTONE, BlockTypes.RED_SANDSTONE);
        blockTypes.put(Material.SMOOTH_SANDSTONE, BlockTypes.SANDSTONE);
        blockTypes.put(Material.SMOOTH_STONE, BlockTypes.STONEBRICK);
        blockTypes.put(Material.SNOW, BlockTypes.SNOW_LAYER);
        blockTypes.put(Material.SNOW_BLOCK, BlockTypes.SNOW);
        blockTypes.put(Material.SOUL_SAND, BlockTypes.SOUL_SAND);
        blockTypes.put(Material.SPAWNER, BlockTypes.MOB_SPAWNER);
        blockTypes.put(Material.SPONGE, BlockTypes.SPONGE);
        blockTypes.put(Material.SPRUCE_BUTTON, BlockTypes.WOODEN_BUTTON);
        blockTypes.put(Material.SPRUCE_DOOR, BlockTypes.SPRUCE_DOOR);
        blockTypes.put(Material.SPRUCE_FENCE, BlockTypes.SPRUCE_FENCE);
        blockTypes.put(Material.SPRUCE_FENCE_GATE, BlockTypes.SPRUCE_FENCE_GATE);
        blockTypes.put(Material.SPRUCE_LEAVES, BlockTypes.LEAVES);
        blockTypes.put(Material.SPRUCE_LOG, BlockTypes.LOG);
        blockTypes.put(Material.SPRUCE_PLANKS, BlockTypes.PLANKS);
        blockTypes.put(Material.SPRUCE_PRESSURE_PLATE, BlockTypes.WOODEN_PRESSURE_PLATE);
        blockTypes.put(Material.SPRUCE_SAPLING, BlockTypes.SAPLING);
        blockTypes.put(Material.SPRUCE_SLAB, BlockTypes.WOODEN_SLAB);
        blockTypes.put(Material.SPRUCE_STAIRS, BlockTypes.SPRUCE_STAIRS);
        blockTypes.put(Material.SPRUCE_TRAPDOOR, BlockTypes.TRAPDOOR);
        blockTypes.put(Material.SPRUCE_WOOD, BlockTypes.LOG);
        blockTypes.put(Material.STICKY_PISTON, BlockTypes.STICKY_PISTON);
        blockTypes.put(Material.STONE, BlockTypes.STONE);
        blockTypes.put(Material.STONE_BRICKS, BlockTypes.STONEBRICK);
        blockTypes.put(Material.STONE_BRICK_SLAB, BlockTypes.STONE_SLAB);
        blockTypes.put(Material.STONE_BRICK_STAIRS, BlockTypes.STONE_BRICK_STAIRS);
        blockTypes.put(Material.STONE_BUTTON, BlockTypes.STONE_BUTTON);
        blockTypes.put(Material.STONE_PRESSURE_PLATE, BlockTypes.STONE_PRESSURE_PLATE);
        blockTypes.put(Material.STONE_SLAB, BlockTypes.STONE_SLAB);
        blockTypes.put(Material.STRUCTURE_BLOCK, BlockTypes.STRUCTURE_BLOCK);
        blockTypes.put(Material.STRUCTURE_VOID, BlockTypes.STRUCTURE_VOID);
        blockTypes.put(Material.SUGAR_CANE, BlockTypes.REEDS);
        blockTypes.put(Material.SUNFLOWER, BlockTypes.DOUBLE_PLANT);
        blockTypes.put(Material.TALL_GRASS, BlockTypes.TALLGRASS);
        blockTypes.put(Material.TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.TNT, BlockTypes.TNT);
        blockTypes.put(Material.TORCH, BlockTypes.TORCH);
        blockTypes.put(Material.TRAPPED_CHEST, BlockTypes.TRAPPED_CHEST);
        blockTypes.put(Material.TRIPWIRE, BlockTypes.TRIPWIRE);
        blockTypes.put(Material.TRIPWIRE_HOOK, BlockTypes.TRIPWIRE_HOOK);
        blockTypes.put(Material.VINE, BlockTypes.VINE);
        blockTypes.put(Material.WALL_SIGN, BlockTypes.WALL_SIGN);
        blockTypes.put(Material.WALL_TORCH, BlockTypes.TORCH);
        blockTypes.put(Material.WATER, BlockTypes.WATER);
        blockTypes.put(Material.WET_SPONGE, BlockTypes.SPONGE);
        blockTypes.put(Material.WHEAT, BlockTypes.WHEAT);
        blockTypes.put(Material.WHITE_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.WHITE_BED, BlockTypes.BED);
        blockTypes.put(Material.WHITE_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.WHITE_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.WHITE_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.WHITE_GLAZED_TERRACOTTA, BlockTypes.WHITE_GLAZED_TERRACOTTA);
        blockTypes.put(Material.WHITE_SHULKER_BOX, BlockTypes.WHITE_SHULKER_BOX);
        blockTypes.put(Material.WHITE_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.WHITE_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.WHITE_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.WHITE_TULIP, BlockTypes.RED_FLOWER);
        blockTypes.put(Material.WHITE_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.WHITE_WOOL, BlockTypes.WOOL);
        blockTypes.put(Material.YELLOW_BANNER, BlockTypes.STANDING_BANNER);
        blockTypes.put(Material.YELLOW_BED, BlockTypes.BED);
        blockTypes.put(Material.YELLOW_CARPET, BlockTypes.CARPET);
        blockTypes.put(Material.YELLOW_CONCRETE, BlockTypes.CONCRETE);
        blockTypes.put(Material.YELLOW_CONCRETE_POWDER, BlockTypes.CONCRETE_POWDER);
        blockTypes.put(Material.YELLOW_GLAZED_TERRACOTTA, BlockTypes.YELLOW_GLAZED_TERRACOTTA);
        blockTypes.put(Material.YELLOW_SHULKER_BOX, BlockTypes.YELLOW_SHULKER_BOX);
        blockTypes.put(Material.YELLOW_STAINED_GLASS, BlockTypes.STAINED_GLASS);
        blockTypes.put(Material.YELLOW_STAINED_GLASS_PANE, BlockTypes.STAINED_GLASS_PANE);
        blockTypes.put(Material.YELLOW_TERRACOTTA, BlockTypes.STAINED_HARDENED_CLAY);
        blockTypes.put(Material.YELLOW_WALL_BANNER, BlockTypes.WALL_BANNER);
        blockTypes.put(Material.YELLOW_WOOL, BlockTypes.WOOL);

        // TODO: Item types
    }

    public static int getTypeId(BlockType type) {
        Material material = toMaterial(type);

        return material.getId();
    }

    public static Material fromTypeId(int type) {
        for (Material material : blockTypes.keySet()) {
            if (material.getId() == type) {
                return material;
            }
        }

        return Material.AIR;
    }

    public static Material toMaterial(BlockType type) {
        Optional<Map.Entry<Material,BlockType>> entry = blockTypes.entrySet().stream().filter(e -> e.getValue().equals(type)).findAny();

        if (!entry.isPresent()) {
            return Material.AIR;
        }

        return entry.get().getKey();
    }

    public static BlockType toBlockType(Material material) {
        BlockType type = blockTypes.get(material);

        if (material == null) {
            return BlockTypes.AIR;
        }

        return type;
    }
}
