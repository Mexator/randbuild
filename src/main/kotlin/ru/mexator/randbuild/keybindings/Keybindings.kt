package ru.mexator.randbuild.keybindings

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

object Keybindings {
    private const val RandbuildCategory = "Randbuild mod"

    private var _toggleSlotBinding: KeyBinding? = null
    private var _resetSlotsBinding: KeyBinding? = null
    val toggleSlotBinding: KeyBinding get() = _toggleSlotBinding ?: notInitializedError()
    val resetSlotsBinding: KeyBinding get() = _resetSlotsBinding ?: notInitializedError()

    fun register() {
        _toggleSlotBinding = KeyBindingHelper.registerKeyBinding(
            KeyBinding(
                "key.lock_slot",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                RandbuildCategory
            )
        )
        _resetSlotsBinding = KeyBindingHelper.registerKeyBinding(
            KeyBinding(
                "key.reset_locks",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                RandbuildCategory
            )
        )
    }

    private fun notInitializedError(): Nothing = error("Accessing key bindings before mod is initialized")
}