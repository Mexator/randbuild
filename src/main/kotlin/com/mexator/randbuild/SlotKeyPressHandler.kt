package com.mexator.randbuild

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.screen.slot.Slot
import org.lwjgl.glfw.GLFW

@Environment(EnvType.CLIENT)
class SlotKeyPressHandler(
    private val registry: CheckedSlotsRegistry,
    private val isSlotHandled: IsSlotHandled
) {
    companion object {
        val toggleSlotBinding: KeyBinding = KeyBindingHelper.registerKeyBinding(
            KeyBinding(
                "key.slotlock",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                "key.categories.inventory"
            )
        )
    }

    fun handle(focusedSlot: Slot, keyCode: Int, scanCode: Int) {
        if (toggleSlotBinding.matchesKey(keyCode, scanCode)) {
            handleToggleClick(focusedSlot)
        }
    }

    private fun handleToggleClick(focusedSlot: Slot) {
        if (isSlotHandled(focusedSlot)) {
            registry.toggleSlot(focusedSlot.index)
        }
    }
}