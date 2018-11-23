package com.hqbanana.endgamestuffmod.containers.slots;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotSoulSand extends SlotItemHandler {

	public SlotSoulSand(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if (!(stack.getItem() instanceof ItemBlock)) return false;
		Block block = Block.getBlockFromItem(stack.getItem());
		if (block == Blocks.SOUL_SAND) return true;
		return false;
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return 64;
	}
}
