package com.mexator.randbuild

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.screen.slot.SlotActionType

@Environment(EnvType.CLIENT)
class PlacedBlockTracker(private val registry: CheckedSlotsRegistry) {

    fun onBlockPlaced(placer: LivingEntity) {
        if (placer is ClientPlayerEntity) {
            val swapSource = registry.getAllCheckedSlots().random()

            MinecraftClient.getInstance().interactionManager
                ?.clickSlot(
                    placer.playerScreenHandler.syncId,
                    swapSource,
                    placer.inventory.selectedSlot,
                    SlotActionType.SWAP,
                    placer
                )
        }
    }
}