package com.mexator.randbuild.random

import com.mexator.randbuild.CheckedSlotsRegistry
import net.minecraft.client.MinecraftClient

class BlockRandomizer(private val checkedSlotsRegistry: CheckedSlotsRegistry) {
    fun goodRandomSlotId(): Int {
        val slots = checkedSlotsRegistry.getAllCheckedSlots()
        if (slots.isEmpty()) throw NoSuchElementException()

        val inventory = MinecraftClient.getInstance().player!!.inventory.main

        val nonEmptySlots = slots.filter { inventory[it.index].isEmpty.not() }

        val goodSlot = weightedRandom(nonEmptySlots) { inventory[it.index].count }
        return goodSlot.id
    }
}