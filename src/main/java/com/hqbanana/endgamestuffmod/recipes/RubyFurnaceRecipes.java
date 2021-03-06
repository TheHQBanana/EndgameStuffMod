package com.hqbanana.endgamestuffmod.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.hqbanana.endgamestuffmod.init.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RubyFurnaceRecipes {
	private static final RubyFurnaceRecipes INSTANCE = new RubyFurnaceRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static RubyFurnaceRecipes getInstance() {
		return INSTANCE;
	}
	
	private RubyFurnaceRecipes() {
		addRubyFurnaceRecipe(new ItemStack(Items.APPLE, 1), new ItemStack(Items.DIAMOND, 1), new ItemStack(ModItems.RUBY, 2), 5.0f);
	}
	
	public void addRubyFurnaceRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) {
		if (getRubyResult(input1, input2) != ItemStack.EMPTY) return;
		this.smeltingList.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getRubyResult(ItemStack input1, ItemStack input2) {
		for (Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet()) {
			if (this.compareItemStacks(input1, (ItemStack)entry.getKey())) {
				for (Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
					if (this.compareItemStacks(input2, (ItemStack)ent.getKey())) {
						return (ItemStack)ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	public boolean isPartOfRecipe(ItemStack input1) {
		for (Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet()) {
			if (this.compareItemStacks(input1, (ItemStack)entry.getKey())) return true;
			for (Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
				if (this.compareItemStacks(input1, (ItemStack)ent.getKey())) return true;
			}
		}
		return false;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList() {
		return this.smeltingList;
	}
	
	public float getRubyExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if (this.compareItemStacks(stack, (ItemStack)entry.getKey())) {
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0f;
	}
}
