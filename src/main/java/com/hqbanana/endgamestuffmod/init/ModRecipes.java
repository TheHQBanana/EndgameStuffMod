package com.hqbanana.endgamestuffmod.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	public static void init() {
		GameRegistry.addSmelting(Items.APPLE, new ItemStack(ModItems.RUBY, 1), 0.5f);
	}
}
