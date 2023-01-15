package ru.mexator.randbuild

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.loader.impl.util.log.Log
import net.fabricmc.loader.impl.util.log.LogCategory
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.screen.slot.SlotActionType
import ru.mexator.randbuild.random.BlockRandomizer

/**
 * Whenever player places a block ([onBlockPlaced] call), we wait for server to process placing ([onServerNotified]
 * call) and then replace stack in player's hand.
 *
 * We can't replace stack before server is notified. Otherwise, places block will be the new one. From the player's
 * perspective it feels weird.
 */
@Environment(EnvType.CLIENT)
class PlacedBlockTracker(
    private val randomizer: BlockRandomizer
) {
    class SwapRecord(
        val player: ClientPlayerEntity,
        val swapSlotId: Int,
    )

    private var data: SwapRecord? = null

    fun onBlockPlaced(placer: LivingEntity) {
        if (placer !is ClientPlayerEntity) return
        if (!CheckedSlotsRegistry.isHotbarSlotChecked(placer.inventory.selectedSlot)) return

        Log.debug(LogCategory.LOG, "Player placed a block from slot ${placer.inventory.selectedSlot}")
        val swapSource = try {
            randomizer.goodRandomSlotId(placer)
        } catch (ex: NoSuchElementException) {
            return
        }

        Log.debug(LogCategory.LOG, "Swapping slot ${placer.inventory.selectedSlot} and $swapSource")
        data = SwapRecord(placer,swapSource)
    }

    fun onServerNotified() {
        data?.run {
            MinecraftClient.getInstance().interactionManager
                ?.clickSlot(
                    player.playerScreenHandler.syncId,
                    swapSlotId,
                    player.inventory.selectedSlot,
                    SlotActionType.SWAP,
                    player
                )
        }
        data = null
    }
}