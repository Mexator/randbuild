package com.mexator.randbuild

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.screen.slot.Slot

class IsSlotHandled {
    operator fun invoke(slot: Slot): Boolean {
        return slot.javaClass == Slot::class.java // Regular slot (not armor, shield, creative slot)
                && slot.inventory is PlayerInventory // In player inventory (not in crafting grid, dispenser, etc)
    }
}