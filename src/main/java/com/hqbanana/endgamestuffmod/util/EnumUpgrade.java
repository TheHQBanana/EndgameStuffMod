package com.hqbanana.endgamestuffmod.util;

import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum EnumUpgrade implements IStringSerializable {
    IRON(0, 3, 2, 1, "iron", "iron"),
    GOLD(1, 2, 4, 2, "gold", "gold"),
    DIAMOND(2, 1, 8, 4, "diamond", "diamond"),
	NETHER(3, 0, 16, 8, "nether", "nether");
	//NETHERER(4, 1, 32, 32, "netherer", "netherer"),
	//NETHEREST(5, 0, 64, 64, "netherest", "netherest");

    private static final EnumUpgrade[] META_LOOKUP = new EnumUpgrade[values().length];
    private static final EnumUpgrade[] UPGRADE_DMG_LOOKUP = new EnumUpgrade[values().length];
    private final int meta;
    private final int dyeDamage;
    private final int upgradeValueSpeed;
    private final int upgradeValueEfficiency;
    private final String name;
    private final String translationKey;

    private EnumUpgrade(int metaIn, int dyeDamageIn, int upgradeValueSpeed, int upgradeValueEfficiency, String nameIn, String unlocalizedNameIn) {
        this.meta = metaIn;
        this.dyeDamage = dyeDamageIn;
        this.upgradeValueSpeed = upgradeValueSpeed;
        this.upgradeValueEfficiency = upgradeValueEfficiency;
        this.name = nameIn;
        this.translationKey = unlocalizedNameIn;
    }

    public int getMetadata() {
        return this.meta;
    }

    public int getUpgradeDamage() {
        return this.dyeDamage;
    }
    
    public int getUpgradeValueSpeed() {
    	return this.upgradeValueSpeed;
    }
    
    public int getUpgradeValueEfficiency() {
    	return this.upgradeValueEfficiency;
    }

    @SideOnly(Side.CLIENT)
    public String getDyeColorName() {
        return this.name;
    }

    public String getTranslationKey() {
        return this.translationKey;
    }

    public static EnumUpgrade byUpgradeDamage(int damage) {
        if (damage < 0 || damage >= UPGRADE_DMG_LOOKUP.length) {
            damage = 0;
        }
        
        return UPGRADE_DMG_LOOKUP[damage];
    }

    public static EnumUpgrade byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
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
