package com.hqbanana.endgamestuffmod.tileentities.generators.coal;

import com.hqbanana.endgamestuffmod.util.EnumUpgrade;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityCoalGeneratorAdvanced extends TileEntityCoalGeneratorBase {
	public TileEntityCoalGeneratorAdvanced() {
		super("Advanced coal generator", 200000, 0, 100, 0);
		inventory = new ItemStackHandler(3) {
			@Override
			protected void onContentsChanged(int slot) {
				super.onContentsChanged(slot);
				if (slot > 0) {
					ItemStack itemStack = this.getStackInSlot(slot);
					int modifier = EnumUpgrade.byUpgradeDamage(itemStack.getMetadata()).getUpgradeValue();
					TileEntityCoalGeneratorAdvanced.this.speedUpgradeModifier = modifier;
					TileEntityCoalGeneratorAdvanced.this.modifyMaxEnergy((int)Math.pow(modifier, 2));
				}
				TileEntityCoalGeneratorAdvanced.this.markDirty();
			};
		};
	}
	
	public int speedUpgradeModifier = 0, efficiencyUpgradeModifier = 0;
	
	@Override
	public void update() {
		if (!this.world.isRemote) {
			if (!this.inventory.getStackInSlot(0).isEmpty() || currentBurnTime > 0) {
				int burnTime = getItemBurnTime(this.inventory.getStackInSlot(0));
				if (totalBurnTime == 0 && burnTime > 0) {
					totalBurnTime = burnTime; 
					burnFuel();
				}
				if (currentBurnTime < totalBurnTime && this.getEnergyStored() < this.getMaxEnergyStored()) {// && burnTime > 0) {
					internalReceiveEnergy(Math.max(rfPerTick, (rfPerTick * (int)Math.pow(speedUpgradeModifier, 2))), false);
					currentBurnTime += Math.max(1, (Math.pow(speedUpgradeModifier, 2) - Math.pow(efficiencyUpgradeModifier, 2)));
				} else if (currentBurnTime >= totalBurnTime && currentBurnTime != 0 && totalBurnTime != 0) {
					currentBurnTime = 0;
					totalBurnTime = 0;
				}
			}
			
			transmitEnergy();
			this.markDirty();
		}
	}
}
