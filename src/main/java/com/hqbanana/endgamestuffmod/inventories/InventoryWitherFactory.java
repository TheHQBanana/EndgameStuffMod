package com.hqbanana.endgamestuffmod.inventories;

import com.hqbanana.endgamestuffmod.init.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryWitherFactory extends InventoryBase {
	public InventoryWitherFactory(int size) {
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
		if (slot >= 0 && slot < 4) {
			return stack.getItem() == Item.getItemFromBlock(Blocks.SOUL_SAND);
		} else if (slot >= 4 && slot < 7) {
			return stack.getItem() == Items.SKULL && stack.getItemDamage() == 1;
		} else if (slot >= 7 && slot < 10) {
			return false;
		} else if (slot == 10) {
			return stack.getItem() == ModItems.GENERATOR_UPGRADE_SPEED;
		} else if (slot == 11) {
			return stack.getItem() == ModItems.GENERATOR_UPGRADE_EFFICIENCY;
		}
		return false;
	}
}
