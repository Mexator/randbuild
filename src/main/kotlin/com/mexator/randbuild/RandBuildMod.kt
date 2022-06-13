package com.mexator.randbuild

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer

@Suppress("UNUSED")
@Environment(EnvType.CLIENT)
object RandBuildMod : ModInitializer {
    const val MOD_ID = "randbuild"

    override fun onInitialize() = Unit

    val placeBlockTracker = PlacedBlockTracker(CheckedSlotsRegistry)
    private val isSlotHandled = IsSlotHandled()
    val slotKeyPressHandler = SlotKeyPressHandler(CheckedSlotsRegistry, isSlotHandled)
    val overlayRenderer = SlotOverlayRenderer(isSlotHandled)
}