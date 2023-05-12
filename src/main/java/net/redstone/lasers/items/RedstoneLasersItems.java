package net.redstone.lasers.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.redstone.lasers.blocks.RedstoneLasersBlocks;

public class RedstoneLasersItems {
        
        //Block Items
        public static final Item REDSTONE_LASER = new BlockItem(RedstoneLasersBlocks.REDSTONE_LASER, new Item.Settings());

        //tech items
        public static final Item REDSTONE_SHARD = new RedstoneShardItem(new Item.Settings());
        

        //Item group
        private static final ItemGroup GROUP = FabricItemGroup.builder(new Identifier("redstonelasers", "group")).icon(() -> new ItemStack(RedstoneLasersBlocks.REDSTONE_LASER)).build();

        //add all items to item group
        // ItemGroupEvents.modifyEntriesEvent(GROUP).register(content ->{
        //     content.add(REDSTONE_LASER);
        //     content.add(REDSTONE_SHARD);
        // });
        
        //ItemGroupEvents.modifyEntriesEvent(GROUP).register(entries -> {entries.add(REDSTONE_SHARD);});

        //register items
        public static void registerItems(){
            //block items
            Registry.register(Registries.ITEM, "redstonelasers:redstone_laser", REDSTONE_LASER);

            //tech items
            Registry.register(Registries.ITEM, "redstonelasers:redstone_shard", REDSTONE_SHARD);
        }
}