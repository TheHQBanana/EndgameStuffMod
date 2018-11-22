package com.hqbanana.endgamestuffmod.init;

import java.util.ArrayList;
import java.util.List;
import com.hqbanana.endgamestuffmod.items.ItemBase;
import com.hqbanana.endgamestuffmod.items.generators.ItemGeneratorUpgradeEfficiency;
import com.hqbanana.endgamestuffmod.items.generators.ItemGeneratorUpgradeSpeed;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModItems {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item RUBY = new ItemBase("ruby");
	public static final ItemGeneratorUpgradeSpeed GENERATOR_UPGRADE_SPEED = new ItemGeneratorUpgradeSpeed("generator_upgrade_speed");
	public static final ItemGeneratorUpgradeEfficiency GENERATOR_UPGRADE_EFFICIENCY = new ItemGeneratorUpgradeEfficiency("generator_upgrade_efficiency");
}
