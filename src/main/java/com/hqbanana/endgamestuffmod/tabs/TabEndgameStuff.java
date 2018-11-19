package com.hqbanana.endgamestuffmod.tabs;

import com.hqbanana.endgamestuffmod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabEndgameStuff extends CreativeTabs {
	public TabEndgameStuff(String label) {
		super("endgamestufftab");
	}
	
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.RUBY);
	}
}