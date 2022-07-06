package com.mexator.randbuild

import com.mexator.randbuild.random.BlockRandomizer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.loader.impl.util.log.Log
import net.fabricmc.loader.impl.util.log.LogCategory
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
            Log.debug(LogCategory.LOG,"Player placed a block from slot ${placer.inventory.selectedSlot}")
            val swapSource = try {
                randomizer.goodRandomSlotId()
            } catch (ex: NoSuchElementException) {
                return
            }

            Log.debug(LogCategory.LOG,"Swapping slot ${placer.inventory.selectedSlot} and $swapSource")
            placer.playerScreenHandler.slots.map { it.index }
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