package com.mexator.randbuild

object CheckedSlotsRegistry {
    private val slots = HashSet<Int>()

    fun toggleSlot(slot: Int) {
        if (slot !in slots) {
            slots.add(slot)
        } else {
            slots.remove(slot)
        }
        println("Toggle slot $slot, now ${slot in slots}")
    }

    fun getAllCheckedSlots(): List<Int> {
        return slots.toList()
    }

    fun isChecked(index: Int): Boolean {
        return index in slots
    }
}

