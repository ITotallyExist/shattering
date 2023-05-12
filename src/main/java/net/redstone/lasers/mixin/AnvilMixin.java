package net.redstone.lasers.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.redstone.lasers.items.RedstoneLasersItems;

@Mixin(AnvilBlock.class)
public class AnvilMixin {
    @Inject(at = @At("HEAD"), method = "onLanding")
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity, CallbackInfo info) {
        if (!world.isClient()){
            if (world.getBlockState(pos.down()).getBlock() instanceof RedstoneBlock){
                System.out.println("landed on redstone block");
                world.removeBlock(pos.down(), false);
                ItemScatterer.spawn(world, pos, DefaultedList.copyOf(ItemStack.EMPTY, new ItemStack(RedstoneLasersItems.REDSTONE_SHARD),new ItemStack(RedstoneLasersItems.REDSTONE_SHARD),new ItemStack(RedstoneLasersItems.REDSTONE_SHARD)));
            }
        }
    }
}