package com.hqbanana.endgamestuffmod.inventories;

import com.hqbanana.endgamestuffmod.init.ModItems;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorBase;

import net.minecraft.item.ItemStack;

public class InventoryCoalGenerator extends InventoryBase {
	public InventoryCoalGenerator(int size) {
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
			return TileEntityCoalGeneratorBase.getItemBurnTime(stack) > 0;
		} else if (slot == 1) {
			return stack.getItem() == ModItems.GENERATOR_UPGRADE_SPEED;
		} else if (slot == 2) {
			return stack.getItem() == ModItems.GENERATOR_UPGRADE_EFFICIENCY;
		}
		return false;
	}
}
