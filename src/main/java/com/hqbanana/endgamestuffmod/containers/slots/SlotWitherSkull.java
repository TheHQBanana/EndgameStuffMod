package com.hqbanana.endgamestuffmod.containers.slots;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotWitherSkull extends SlotItemHandler {
	public SlotWitherSkull(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		//if (!(stack.getItem() instanceof ItemBlock)) return false;
		//Block block = Block.getBlockFromItem(stack.getItem());
		//if ((block == Blocks.SKULL && stack.getItemDamage() == ) return true;
		if (stack.getItem() == Items.SKULL && stack.getItemDamage() == 1) return true;
		return false;
		//return false;
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return 64;
	}
}
