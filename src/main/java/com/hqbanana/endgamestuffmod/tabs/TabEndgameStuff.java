package com.hqbanana.endgamestuffmod.tabs;

import com.hqbanana.endgamestuffmod.init.ModFluids;
import com.hqbanana.endgamestuffmod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class TabEndgameStuff extends CreativeTabs {
	public TabEndgameStuff(String label) {
		super("endgamestufftab");
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.RUBY);
	}
	
	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> p_78018_1_) {
		super.displayAllRelevantItems(p_78018_1_);
		p_78018_1_.add(FluidUtil.getFilledBucket(new FluidStack(ModFluids.LIQUID_XP, 1000)));
	}
}
