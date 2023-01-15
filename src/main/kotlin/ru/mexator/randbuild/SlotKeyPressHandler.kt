package ru.mexator.randbuild

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.screen.slot.Slot
import ru.mexator.randbuild.keybindings.Keybindings.toggleSlotBinding

@Environment(EnvType.CLIENT)
class SlotKeyPressHandler(
    private val registry: CheckedSlotsRegistry,
    private val isSlotHandled: IsSlotHandled
) {

    fun handle(focusedSlot: Slot, keyCode: Int, scanCode: Int) {
        if (toggleSlotBinding.matchesKey(keyCode, scanCode)) {
            handleToggleClick(focusedSlot)
        }
    }

    private fun handleToggleClick(focusedSlot: Slot) {
        if (isSlotHandled(focusedSlot)) {
            registry.toggleSlot(focusedSlot)
        }
    }
}