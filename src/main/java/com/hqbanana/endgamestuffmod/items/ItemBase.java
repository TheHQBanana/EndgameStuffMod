package com.hqbanana.endgamestuffmod.items;

import com.hqbanana.endgamestuffmod.Main;
import com.hqbanana.endgamestuffmod.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item {
	public ItemBase(String name) {
		setTranslationKey(name);
		setRegistryName(name);
		setCreativeTab(Main.ENDGAME_STUFF_TAB);
		ModItems.ITEMS.add(this);
	}
}
