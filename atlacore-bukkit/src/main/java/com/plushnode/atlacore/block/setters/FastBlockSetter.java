package com.plushnode.atlacore.block.setters;

import com.plushnode.atlacore.platform.BlockStateWrapper;
import com.plushnode.atlacore.platform.BlockWrapper;
import com.plushnode.atlacore.platform.Location;
import com.plushnode.atlacore.platform.block.Block;
import com.plushnode.atlacore.platform.block.BlockSetter;
import com.plushnode.atlacore.platform.block.BlockState;
import com.plushnode.atlacore.platform.block.Material;
import com.plushnode.atlacore.platform.block.data.BlockData;
import com.plushnode.atlacore.util.TypeUtil;
import com.plushnode.atlacore.platform.LocationWrapper;

public class FastBlockSetter implements BlockSetter {
    @Override
    public void setBlock(Location location, Material material) {
        LocationWrapper wrapper = (LocationWrapper)location;
        org.bukkit.Material bukkitMaterial = TypeUtil.adapt(material);

        NativeMethods.setBlockFast(wrapper.getBukkitLocation().getBlock(), bukkitMaterial.createBlockData());
    }

    @Override
    public void setBlock(Block block, Material material) {
        setBlock(block.getLocation(), material);
    }

    @Override
    public void setBlock(Block block, Material material, BlockData data) {
        org.bukkit.Material bukkitMaterial = TypeUtil.adapt(material);
        org.bukkit.block.data.BlockData blockData = bukkitMaterial.createBlockData();

        if (data != null) {
            blockData = TypeUtil.adapt(data);
        }

        NativeMethods.setBlockFast(((BlockWrapper)block).getBukkitBlock(), blockData);
    }

    @Override
    public void setBlock(BlockState state) {
        org.bukkit.block.BlockState bukkitState = ((BlockStateWrapper) state).getBukkitState();

        NativeMethods.setBlockFast(((BlockWrapper)state.getBlock()).getBukkitBlock(), bukkitState.getBlockData());
    }
}
