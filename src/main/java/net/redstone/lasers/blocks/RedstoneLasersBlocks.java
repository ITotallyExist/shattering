package net.redstone.lasers.blocks;

import net.minecraft.block.Block;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public class RedstoneLasersBlocks {
    public static final Block REDSTONE_LASER = new RedstoneLaser();

    public static void registerBlocks(){
        Registry.register(Registries.BLOCK, "redstonelasers:redstone_laser", REDSTONE_LASER);
    }

    public static void putRenderLayers(){
    }
}