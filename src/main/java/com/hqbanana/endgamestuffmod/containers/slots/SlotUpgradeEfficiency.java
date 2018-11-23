package com.hqbanana.endgamestuffmod.containers.slots;

import com.hqbanana.endgamestuffmod.items.generators.ItemGeneratorUpgradeEfficiency;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandler;

public class SlotUpgradeEfficiency extends SlotUpgrade {
	public SlotUpgradeEfficiency(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	public SlotUpgradeEfficiency(IItemHandler itemHandler, int index, int xPosition, int yPosition, ResourceLocation emptySlotBackgroundLocation, String emptySlotName) {
		super(itemHandler, index, xPosition, yPosition);
		this.setBackgroundLocation(emptySlotBackgroundLocation);
		this.setBackgroundName(emptySlotName);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() instanceof ItemGeneratorUpgradeEfficiency;
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return 1;
	}
}
