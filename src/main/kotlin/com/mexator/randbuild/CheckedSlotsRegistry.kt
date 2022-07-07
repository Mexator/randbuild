package com.mexator.randbuild

import net.minecraft.screen.slot.Slot

object CheckedSlotsRegistry {
    private val slots = HashSet<Slot>()

    fun toggleSlot(slot: Slot) {
        if (slot !in slots) {
            slots.add(slot)
        } else {
            slots.remove(slot)
        }
        println("Toggle slot $slot, now ${slot in slots}")
    }

    fun getAllCheckedSlots(): List<Slot> {
        return slots.toList()
    }

    fun isChecked(slot: Slot): Boolean {
        return slot in slots
    }

    fun clear() {
        slots.clear()
    }
}

