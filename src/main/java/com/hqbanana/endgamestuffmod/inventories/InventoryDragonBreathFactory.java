package com.hqbanana.endgamestuffmod.inventories;

import com.hqbanana.endgamestuffmod.init.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryDragonBreathFactory extends InventoryBase {
	public InventoryDragonBreathFactory(int size) {
		super(size);
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
		if (slot == 0) {
			return stack.getItem() == Item.getItemFromBlock(Blocks.DRAGON_EGG);
		} else if (slot == 1) {
			return stack.getItem() == Items.END_CRYSTAL;
		} else if (slot == 2) {
			return false;
		} else if (slot == 3) {
			return stack.getItem() == ModItems.GENERATOR_UPGRADE_SPEED;
		} else if (slot == 4) {
			return stack.getItem() == ModItems.GENERATOR_UPGRADE_EFFICIENCY;
		}
		return false;
	}
}
