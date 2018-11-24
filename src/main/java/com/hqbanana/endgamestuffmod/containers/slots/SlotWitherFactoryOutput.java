package com.hqbanana.endgamestuffmod.containers.slots;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotWitherFactoryOutput extends SlotItemHandler {
	public SlotWitherFactoryOutput(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() == Items.NETHER_STAR;
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return 64;
	}
}
