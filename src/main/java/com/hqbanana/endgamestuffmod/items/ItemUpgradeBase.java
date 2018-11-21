package com.hqbanana.endgamestuffmod.items;

import com.hqbanana.endgamestuffmod.util.EnumUpgrade;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemUpgradeBase extends ItemBase {
	public static final String[] UPGRADE_TYPES = new String[] { "iron", "gold", "diamond" };
	
	public ItemUpgradeBase(String name) {
		super(name);
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
	}
	
	public String getTranslationKey(ItemStack stack) {
        int i = stack.getMetadata();
        return super.getTranslationKey() + "." + EnumUpgrade.byUpgradeDamage(i).getTranslationKey();
    }
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (int i = 0; i < 3; ++i) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }
}
