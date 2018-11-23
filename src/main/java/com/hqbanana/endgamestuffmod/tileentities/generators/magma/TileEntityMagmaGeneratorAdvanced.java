package com.hqbanana.endgamestuffmod.tileentities.generators.magma;

import com.hqbanana.endgamestuffmod.util.EnumUpgrade;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityMagmaGeneratorAdvanced extends TileEntityMagmaGeneratorBase {
	public TileEntityMagmaGeneratorAdvanced() {
		super("Advanced magma generator", 5000000, 0, 500000, 0);
		fluidTank = new FluidTank(8000) {
			@Override
			protected void onContentsChanged() {
				super.onContentsChanged();
				TileEntityMagmaGeneratorAdvanced.this.markDirty();
			};
		};
		
		inventory = new ItemStackHandler(2) {
			@Override
			protected void onContentsChanged(int slot) {
				super.onContentsChanged(slot);
				switch(slot) {
				case 0:
					TileEntityMagmaGeneratorAdvanced.this.updateSpeedModifier(slot);
					break;
				case 1: 
					TileEntityMagmaGeneratorAdvanced.this.updateEfficiencyModifier(slot);
					break;
				}
				TileEntityMagmaGeneratorAdvanced.this.markDirty();
			};
		};
		
		fluidTank.setCanDrain(false);
		rfPerTick = 80;
	}
	
	public int speedUpgradeModifier = 0, efficiencyUpgradeModifier = 0;
	private ItemStack currentSpeedUpgrade = null, currentEfficiencyUpgrade = null;
	
	@Override
	public void update() {
		if (!this.world.isRemote) {
			if (this.fluidTank.getFluidAmount() >= 1000) {
				int burnTime = getFluidBurnTime(this.fluidTank.getFluid());
				if (totalBurnTime == 0 && burnTime > 0) {
					totalBurnTime = burnTime; 
					burnFuel();
				}
				if (currentBurnTime < totalBurnTime && this.getEnergyStored() < this.getMaxEnergyStored() && burnTime > 0) {
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
			//modifyMaxEnergy((int)Math.pow(speedModifier, 2), false);
			//CREATE UPGRADE SPECIFICALLY FOR UPGRADING ENERGY STORAGE!
			currentSpeedUpgrade = itemStack;
		} else if (itemStack.isEmpty() && currentSpeedUpgrade != null) {
			//modifyMaxEnergy((int)Math.pow(EnumUpgrade.byUpgradeDamage(currentSpeedUpgrade.getMetadata()).getUpgradeValueSpeed(), 2), true);
			currentSpeedUpgrade = null;
			speedUpgradeModifier = 0;
			//if (getEnergyStored() > getMaxEnergyStored()) internalExtractEnergy((getEnergyStored() - getMaxEnergyStored()), false);
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
