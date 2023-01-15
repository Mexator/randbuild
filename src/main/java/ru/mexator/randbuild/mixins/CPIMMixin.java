package ru.mexator.randbuild.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import org.apache.commons.lang3.mutable.MutableObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import ru.mexator.randbuild.PlacedBlockTracker;
import ru.mexator.randbuild.RandBuildMod;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerInteractionManager.class)
public class CPIMMixin {
    private final PlacedBlockTracker tracker = RandBuildMod.INSTANCE.getPlaceBlockTracker();

    @Inject(at = @At(value = "RETURN", ordinal = 1), method = "interactBlock", locals = LocalCapture.CAPTURE_FAILSOFT)
    void interactBlock(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir, MutableObject<ActionResult> mutableObject) {
        if (player != null && mutableObject.getValue() == ActionResult.SUCCESS) {
            tracker.onServerNotified();
        }
    }
}
