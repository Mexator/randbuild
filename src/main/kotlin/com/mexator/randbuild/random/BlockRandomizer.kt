package com.mexator.randbuild.random

import com.mexator.randbuild.CheckedSlotsRegistry
import net.minecraft.client.MinecraftClient

class BlockRandomizer(private val checkedSlotsRegistry: CheckedSlotsRegistry) {
    fun goodRandomSlot(): Int {
        val slots = checkedSlotsRegistry.getAllCheckedSlots()
        if (slots.isEmpty()) throw NoSuchElementException()

        val inventory = MinecraftClient.getInstance().player!!.inventory.main

        val nonEmptySlots = slots.filter { inventory[it].isEmpty.not() }

        return weightedRandom(nonEmptySlots) { inventory[it].count }
    }
}