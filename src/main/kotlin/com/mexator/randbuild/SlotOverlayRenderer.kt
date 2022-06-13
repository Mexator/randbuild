package com.mexator.randbuild

import com.mojang.blaze3d.systems.RenderSystem
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.screen.slot.Slot
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
class SlotOverlayRenderer(private val isSlotHandled: IsSlotHandled) {
    companion object {
        private val SLOT_LOCK_TEXTURE = Identifier(RandBuildMod.MOD_ID, "textures/gui/lock_overlay.png")
    }

    fun render(matrices: MatrixStack, slot: Slot, screen: Screen) {
        if (!isSlotHandled(slot)) return
        if (!CheckedSlotsRegistry.isChecked(slot.index)) return

        RenderSystem.setShaderTexture(0, SLOT_LOCK_TEXTURE)
        with(screen) {
            zOffset += 1000
            drawTexture(matrices, slot.x, slot.y, 0, 0, 16, 16)
            zOffset -= 1000
        }
    }
}