package com.hqbanana.endgamestuffmod.util;

import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum EnumUpgrade implements IStringSerializable
{
    IRON(0, 2, 2, "iron", "iron"),
    GOLD(1, 1, 4, "gold", "gold"),
    DIAMOND(2, 0, 8, "diamond", "diamond");

    private static final EnumUpgrade[] META_LOOKUP = new EnumUpgrade[values().length];
    private static final EnumUpgrade[] UPGRADE_DMG_LOOKUP = new EnumUpgrade[values().length];
    private final int meta;
    private final int dyeDamage;
    private final int upgradeValue;
    private final String name;
    private final String translationKey;

    private EnumUpgrade(int metaIn, int dyeDamageIn, int upgradeValue, String nameIn, String unlocalizedNameIn) {
        this.meta = metaIn;
        this.dyeDamage = dyeDamageIn;
        this.upgradeValue = upgradeValue;
        this.name = nameIn;
        this.translationKey = unlocalizedNameIn;
    }

    public int getMetadata() {
        return this.meta;
    }

    public int getUpgradeDamage() {
        return this.dyeDamage;
    }
    
    public int getUpgradeValue() {
    	return this.upgradeValue;
    }

    @SideOnly(Side.CLIENT)
    public String getDyeColorName() {
        return this.name;
    }

    public String getTranslationKey() {
        return this.translationKey;
    }

    public static EnumUpgrade byUpgradeDamage(int damage) {
        if (damage < 0 || damage >= UPGRADE_DMG_LOOKUP.length)
        {
            damage = 0;
        }
        
        return UPGRADE_DMG_LOOKUP[damage];
    }

    public static EnumUpgrade byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public String toString() {
        return this.translationKey;
    }

    public String getName() {
        return this.name;
    }

    static {
        for (EnumUpgrade enumUpgrade : values()) {
            META_LOOKUP[enumUpgrade.getMetadata()] = enumUpgrade;
            UPGRADE_DMG_LOOKUP[enumUpgrade.getUpgradeDamage()] = enumUpgrade;
        }
    }
}
