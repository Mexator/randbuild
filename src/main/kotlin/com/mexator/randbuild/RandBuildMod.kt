package com.mexator.randbuild

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.screen.slot.Slot
import org.lwjgl.glfw.GLFW

@Suppress("UNUSED")
object RandBuildMod : ModInitializer {
    const val MOD_ID = "randbuild"
    private val toggleSlotBinding = KeyBindingHelper.registerKeyBinding(
        KeyBinding(
            "key.slotlock",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_ALT,
            "key.categories.inventory"
        )
    )

    override fun onInitialize() = Unit

    fun keyPressed(
        focusedSlot: Slot?,
        keyCode: Int,
        scanCode: Int,
    ) {
        if (focusedSlot == null) return
        if (toggleSlotBinding.matchesKey(keyCode, scanCode)) {
            CheckedSlotsRegistry.toggleSlot(focusedSlot.index)
        }
    }
}