package com.mexator.randbuild.mixins;

import com.mexator.randbuild.RandBuildMod;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HandledScreen.class)
public abstract class SlotMixin extends Screen {
    @Shadow
    @Nullable
    protected Slot focusedSlot;

    protected SlotMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "keyPressed")
    public void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> info) {
        if (focusedSlot != null)
            RandBuildMod.INSTANCE.getSlotKeyPressHandler().handle(focusedSlot, keyCode, scanCode);
    }

    @Inject(at = @At(value = "RETURN", ordinal = 1), method = "drawSlot")
    public void drawSlot(MatrixStack matrices, Slot slot, CallbackInfo info) {
        RandBuildMod.INSTANCE.getOverlayRenderer().render(matrices, slot, this);
    }
}
