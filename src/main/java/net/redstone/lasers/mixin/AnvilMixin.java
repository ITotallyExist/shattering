package net.redstone.lasers.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.redstone.lasers.items.RedstoneLasersItems;

@Mixin(AnvilBlock.class)
public class AnvilMixin {
    @Inject(at = @At("HEAD"), method = "onLanding")
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity, CallbackInfo info) {
        if (!world.isClient()){
            //block that is getting shattered maybe
            BlockPos victimPos = pos.down();

            BlockState victimState = world.getBlockState(victimPos);

            //get identifier of block you landed on
            Identifier victimBlockId = Registries.BLOCK.getId(victimState.getBlock());

            
            //use that identifier to get the identifier for the loot table for shattering this block
            Identifier shatterLootId = new Identifier("redstonelasers:shattering/"+victimBlockId.getPath());

            System.out.println("Shatter loot id: "+shatterLootId.toString());

            //get loot table
            LootTable lootTable = world.getServer().getLootManager().getTable(shatterLootId);

            //see if that loot table exists
            if (!(lootTable == LootTable.EMPTY)){//if it does, shatter block
                System.out.println("shattering: "+victimBlockId.toString());

                //get loot from loot table
                
                    
                    //get loot context
                        //get origin
                
                Vec3d origin = new Vec3d(victimPos.getX(), victimPos.getY(), victimPos.getZ());
                        //get loot context builder
                LootContext.Builder lootContext = new LootContext.Builder((ServerWorld) world)
                    .parameter(LootContextParameters.ORIGIN, origin)
                    .parameter(LootContextParameters.BLOCK_STATE, victimState)
                    .parameter(LootContextParameters.TOOL, new ItemStack(Items.ANVIL));
                
                    //generate loot itemstack
                List<ItemStack> lootStack = lootTable.generateLoot(lootContext.build(LootContextTypes.BLOCK));

                    //generate loot inventory
                Inventory lootInventory = new SimpleInventory(lootStack.toArray(new ItemStack[0]));
                
                //spawn items from loot inventory
                ItemScatterer.spawn(world, pos, lootInventory);

                //destroy block without triggering its regular loot table
                world.removeBlock(victimPos, false);

            }
            

            

            // if (world.getBlockState(pos.down()).getBlock() instanceof RedstoneBlock){
            //     System.out.println("landed on redstone block");
            //     world.removeBlock(pos.down(), false);
            //     ItemScatterer.spawn(world, pos, DefaultedList.copyOf(ItemStack.EMPTY, new ItemStack(RedstoneLasersItems.REDSTONE_SHARD),new ItemStack(RedstoneLasersItems.REDSTONE_SHARD),new ItemStack(RedstoneLasersItems.REDSTONE_SHARD)));
            // }
        }
    }
}