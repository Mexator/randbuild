package com.mexator.randbuild.mixins;

import com.mexator.randbuild.CheckedSlotsRegistry;
import com.mexator.randbuild.RandBuildMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
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
        assert focusedSlot != null;
        RandBuildMod.INSTANCE.keyPressed(focusedSlot, keyCode, scanCode);
    }


    private static final Identifier SLOT_LOCK_TEXTURE = new Identifier(RandBuildMod.MOD_ID, "textures/gui/lock_overlay.png");


    @Inject(at = @At("HEAD"), method = "drawSlot")
    public void drawSlot(MatrixStack matrices, Slot slot, CallbackInfo info) {
        if (this.client != null && CheckedSlotsRegistry.INSTANCE.isChecked(slot.getIndex())) {
            RenderSystem.setShaderTexture(0, SLOT_LOCK_TEXTURE);
            this.drawTexture(matrices, slot.x, slot.y, 0, 0, 16, 16);
        }
    }
}
