package com.hqbanana.endgamestuffmod.tileentities.generators.coal;

import com.hqbanana.endgamestuffmod.util.EnumUpgrade;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityCoalGeneratorAdvanced extends TileEntityCoalGeneratorBase {
	public TileEntityCoalGeneratorAdvanced() {
		super("Advanced coal generator", 200000, 0, 100, 0);
		inventory = new ItemStackHandler(3) {
			@Override
			protected void onContentsChanged(int slot) {
				super.onContentsChanged(slot);
				switch(slot) {
				case 1:
					TileEntityCoalGeneratorAdvanced.this.updateSpeedModifier(slot);
					break;
				case 2: 
					TileEntityCoalGeneratorAdvanced.this.updateEfficiencyModifier(slot);
					break;
				}
				TileEntityCoalGeneratorAdvanced.this.markDirty();
			};
		};
	}
	
	public int speedUpgradeModifier = 0, efficiencyUpgradeModifier = 0;
	private ItemStack currentSpeedUpgrade = null, currentEfficiencyUpgrade = null;
	
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
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setInteger("SpeedUpgradeModifier", this.speedUpgradeModifier);
		compound.setInteger("EfficiencyUpgradeModifier", this.efficiencyUpgradeModifier);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.speedUpgradeModifier = compound.getInteger("SpeedUpgradeModifier");
		this.efficiencyUpgradeModifier = compound.getInteger("EfficiencyUpgradeModifier");
	}
	
	private void updateSpeedModifier(int slot) {
		ItemStack itemStack = this.inventory.getStackInSlot(slot);
		if (!itemStack.isEmpty()) {
			int speedModifier = EnumUpgrade.byUpgradeDamage(itemStack.getMetadata()).getUpgradeValueSpeed();
			speedUpgradeModifier = speedModifier;
			modifyMaxEnergy((int)Math.pow(speedModifier, 2), false);
			currentSpeedUpgrade = itemStack;
		} else if (itemStack.isEmpty() && currentSpeedUpgrade != null) {
			modifyMaxEnergy((int)Math.pow(EnumUpgrade.byUpgradeDamage(currentSpeedUpgrade.getMetadata()).getUpgradeValueSpeed(), 2), true);
			currentSpeedUpgrade = null;
			if (getEnergyStored() > getMaxEnergyStored()) internalExtractEnergy((getEnergyStored() - getMaxEnergyStored()), false);
		}
	}
	
	private void updateEfficiencyModifier(int slot) {
		ItemStack itemStack = this.inventory.getStackInSlot(slot);
		if (!itemStack.isEmpty()) {
			int efficiencyModifier = EnumUpgrade.byUpgradeDamage(itemStack.getMetadata()).getUpgradeValueEfficiency();
			efficiencyUpgradeModifier = efficiencyModifier;
			currentEfficiencyUpgrade = itemStack;
		} else if (itemStack.isEmpty() && currentEfficiencyUpgrade != null) {
			currentEfficiencyUpgrade = null;
			efficiencyUpgradeModifier = 0;
		}
	}
}
