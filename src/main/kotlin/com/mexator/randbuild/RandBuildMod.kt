package com.mexator.randbuild

import com.mexator.randbuild.keybindings.Keybindings
import com.mexator.randbuild.random.BlockRandomizer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW

@Suppress("UNUSED")
@Environment(EnvType.CLIENT)
object RandBuildMod : ModInitializer {
    const val MOD_ID = "randbuild"

    override fun onInitialize() {
        ClientTickEvents.END_CLIENT_TICK.register {
            if (Keybindings.resetSlotsBinding.wasPressed()) {
                CheckedSlotsRegistry.clear()
            }
        }
    }

    private val isSlotHandled = IsSlotHandled()

    // Picking and swapping items
    private val randomizer = BlockRandomizer(CheckedSlotsRegistry)
    val placeBlockTracker = PlacedBlockTracker(randomizer)

    // Drawing and selecting slots
    val slotKeyPressHandler = SlotKeyPressHandler(CheckedSlotsRegistry, isSlotHandled)
    val overlayRenderer = SlotOverlayRenderer(isSlotHandled)
}