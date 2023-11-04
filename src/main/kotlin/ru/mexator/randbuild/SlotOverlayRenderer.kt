package ru.mexator.randbuild

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.gui.DrawContext
import net.minecraft.screen.slot.Slot
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
class SlotOverlayRenderer(private val isSlotHandled: IsSlotHandled) {
    companion object {
        private val SLOT_LOCK_TEXTURE = Identifier(RandBuildMod.MOD_ID, "textures/gui/lock_overlay.png")
        private const val TEXTURE_SIZE = 256 // size of texture image (256x256)
        private const val OVERLAY_Z = 300 // The overlay layer should be between item (starts 150) and tooltip (is at 400)
    }

    fun render(context: DrawContext, slot: Slot) {
        if (!isSlotHandled(slot)) return
        if (!CheckedSlotsRegistry.isChecked(slot)) return

        context.drawTexture(
            /* texture = */ SLOT_LOCK_TEXTURE,
            /* x = */ slot.x,
            /* y = */ slot.y,
            /* z = */ OVERLAY_Z,
            /* u = */ 0f,
            /* v = */ 0f,
            /* width = */ 16,
            /* height = */ 16,
            /* textureWidth = */ TEXTURE_SIZE,
            /* textureHeight = */ TEXTURE_SIZE
        )
    }
}