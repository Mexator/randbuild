package com.mexator.randbuild

import com.mexator.randbuild.random.BlockRandomizer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.screen.slot.SlotActionType

@Environment(EnvType.CLIENT)
class PlacedBlockTracker(
    private val randomizer: BlockRandomizer
) {

    fun onBlockPlaced(placer: LivingEntity) {
        if (placer is ClientPlayerEntity) {
            val swapSource = try {
                randomizer.goodRandomSlot()
            } catch (ex: NoSuchElementException) {
                return
            }

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