package net.redstone.lasers;

import net.fabricmc.api.ModInitializer;
import net.redstone.lasers.blocks.RedstoneLasersBlocks;
import net.redstone.lasers.entities.RedstoneLasersEntities;
import net.redstone.lasers.items.RedstoneLasersItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedstoneLasers implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("redstonelasers");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		//blocks
		RedstoneLasersBlocks.registerBlocks();
		//transparent blocks and such
		RedstoneLasersBlocks.putRenderLayers();
	
		//items
		RedstoneLasersItems.registerItems();

		//entites (including blockentites)
		RedstoneLasersEntities.registerEntities();
	}
}
