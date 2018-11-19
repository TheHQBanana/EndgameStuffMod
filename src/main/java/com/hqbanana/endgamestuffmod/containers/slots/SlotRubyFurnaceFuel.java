package com.hqbanana.endgamestuffmod.containers.slots;

import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnace;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotRubyFurnaceFuel extends Slot {
	public SlotRubyFurnaceFuel(IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityRubyFurnace.isItemFuel(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return super.getItemStackLimit(stack);
	}
}
