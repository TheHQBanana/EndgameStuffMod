package com.hqbanana.endgamestuffmod.containers.slots;

import com.hqbanana.endgamestuffmod.items.generators.ItemGeneratorUpgradeBase;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotUpgrade extends SlotItemHandler {
	public SlotUpgrade(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	public SlotUpgrade(IItemHandler itemHandler, int index, int xPosition, int yPosition, ResourceLocation emptySlotBackgroundLocation, String emptySlotName) {
		super(itemHandler, index, xPosition, yPosition);
		this.setBackgroundLocation(emptySlotBackgroundLocation);	
		this.setBackgroundName(emptySlotName);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof ItemGeneratorUpgradeBase;
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return 1;
	}
	
	@Override
	public int getSlotStackLimit() {
		return 1;
	}
}
