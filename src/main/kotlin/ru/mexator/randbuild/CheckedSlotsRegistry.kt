package ru.mexator.randbuild

import net.minecraft.screen.slot.Slot

object CheckedSlotsRegistry {
    private val slots = HashSet<Slot>()

    fun toggleSlot(slot: Slot) {
        if (slot !in slots) {
            slots.add(slot)
        } else {
            slots.remove(slot)
        }
        println("Toggle slot ${slot.id}, now ${slot in slots}")
    }

    fun getAllCheckedSlots(): List<Slot> {
        return slots.toList()
    }

    fun isChecked(slot: Slot): Boolean {
        return slot in slots
    }

    /**
     * @param slotId from 0-8.
     */
    fun isHotbarSlotChecked(slotId: Int): Boolean {
        for (slot in slots) {
            // transform inventory window slot ids (9-45) to hotbar ones (0-9)
            if(slot.id - 36 == slotId) return true
        }
        return false
    }

    fun clear() {
        slots.clear()
    }
}

