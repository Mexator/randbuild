package ru.mexator.randbuild

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import ru.mexator.randbuild.keybindings.Keybindings
import ru.mexator.randbuild.random.BlockRandomizer

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