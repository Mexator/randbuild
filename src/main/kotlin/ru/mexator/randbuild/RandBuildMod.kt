package ru.mexator.randbuild

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import ru.mexator.randbuild.keybindings.Keybindings
import ru.mexator.randbuild.random.BlockRandomizer

@Suppress("UNUSED")
@Environment(EnvType.CLIENT)
object RandBuildMod : ClientModInitializer {
    const val MOD_ID = "randbuild"

    override fun onInitializeClient() {
        Keybindings.register()
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