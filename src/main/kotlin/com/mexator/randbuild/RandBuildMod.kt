package com.mexator.randbuild

import com.mexator.randbuild.random.BlockRandomizer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer

@Suppress("UNUSED")
@Environment(EnvType.CLIENT)
object RandBuildMod : ModInitializer {
    const val MOD_ID = "randbuild"

    override fun onInitialize() = Unit

    private val isSlotHandled = IsSlotHandled()
    // Picking and swapping items
    private val randomizer = BlockRandomizer(CheckedSlotsRegistry)
    val placeBlockTracker = PlacedBlockTracker(randomizer)
    // Drawing and selecting slots
    val slotKeyPressHandler = SlotKeyPressHandler(CheckedSlotsRegistry, isSlotHandled)
    val overlayRenderer = SlotOverlayRenderer(isSlotHandled)
}