package com.hqbanana.endgamestuffmod.inventories;

import com.hqbanana.endgamestuffmod.init.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class InventoryBase extends ItemStackHandler {
	public InventoryBase(int size) {
        stacks = NonNullList.withSize(size, ItemStack.EMPTY);
    }
	
	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if (isItemValid(slot, stack)) {
			return super.insertItem(slot, stack, simulate);
		}
		return stack;
	}
	
	@Override
	public boolean isItemValid(int slot, ItemStack stack) {
		if (slot == this.getSlots() - 2) {
			return stack.getItem() == ModItems.GENERATOR_UPGRADE_SPEED;
		} else if (slot == this.getSlots() - 1) {
			return stack.getItem() == ModItems.GENERATOR_UPGRADE_EFFICIENCY;
		}
		return false;
	}
}
