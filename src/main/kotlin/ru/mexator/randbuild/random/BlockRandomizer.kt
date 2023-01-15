package ru.mexator.randbuild.random

import net.minecraft.client.network.ClientPlayerEntity
import ru.mexator.randbuild.CheckedSlotsRegistry

class BlockRandomizer(private val checkedSlotsRegistry: CheckedSlotsRegistry) {
    fun goodRandomSlotId(player: ClientPlayerEntity): Int {
        val slots = checkedSlotsRegistry.getAllCheckedSlots()
        if (slots.isEmpty()) throw NoSuchElementException()

        val inventory = player.inventory.main

        val nonEmptySlots = slots.filter { inventory[it.index].isEmpty.not() }

        val goodSlot = weightedRandom(nonEmptySlots) { inventory[it.index].count }
        return goodSlot.id
    }
}