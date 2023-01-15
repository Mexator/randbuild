package ru.mexator.randbuild.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.mexator.randbuild.PlacedBlockTracker;
import ru.mexator.randbuild.RandBuildMod;

@Mixin(Block.class)
public class PlaceMixin {
    private final PlacedBlockTracker tracker = RandBuildMod.INSTANCE.getPlaceBlockTracker();

    @Inject(at = @At("HEAD"), method = "onPlaced")
    void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        if (placer == null) return;
        tracker.onBlockPlaced(placer);
    }
}
