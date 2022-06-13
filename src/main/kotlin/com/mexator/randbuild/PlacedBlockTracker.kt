package com.mexator.randbuild

import net.minecraft.block.Block
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.screen.slot.SlotActionType

interface PlacedBlockTracker {
    fun onBlockPlaced(block: Block, placer: LivingEntity)
}

object PlacedBlockTrackerImpl : PlacedBlockTracker {
    private const val offset = 36

    override fun onBlockPlaced(block: Block, placer: LivingEntity) {
        if (placer is ClientPlayerEntity) {
            val swapSource = CheckedSlotsRegistry.getAllCheckedSlots().random()

            MinecraftClient.getInstance().interactionManager
                ?.clickSlot(
                    placer.playerScreenHandler.syncId,
                    swapSource,
                    placer.inventory.selectedSlot, // dest - no offset
                    SlotActionType.SWAP,
                    placer
                )
        }
    }
}