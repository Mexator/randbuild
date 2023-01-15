package ru.mexator.randbuild.keybindings

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

object Keybindings {
    private const val RandbuildCategory = "Randbuild mod"


    val toggleSlotBinding: KeyBinding = KeyBindingHelper.registerKeyBinding(
        KeyBinding(
            "key.lock_slot",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_ALT,
            RandbuildCategory
        )
    )

    val resetSlotsBinding: KeyBinding = KeyBindingHelper.registerKeyBinding(
        KeyBinding(
            "key.reset_locks",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_MOD_CONTROL or GLFW.GLFW_KEY_R, // Ctrl + R
            RandbuildCategory
        )
    )
}