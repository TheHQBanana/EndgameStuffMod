package com.hqbanana.endgamestuffmod.tileentities.machines;

import com.hqbanana.endgamestuffmod.init.ModFluids;
import com.hqbanana.endgamestuffmod.inventories.InventoryDragonBreathFactory;
import com.hqbanana.endgamestuffmod.power.EnergyStorageBase;
import com.hqbanana.endgamestuffmod.util.EnumUpgrade;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityLiquidXPConverter extends TileEntityMachineBase implements ITickable {
	protected InventoryDragonBreathFactory inventory = new InventoryDragonBreathFactory(3) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			switch(slot) {
			case 1:
				TileEntityLiquidXPConverter.this.updateSpeedModifier(slot);
				break;
			case 2: 
				TileEntityLiquidXPConverter.this.updateEfficiencyModifier(slot);
				break;
			}
			if (slot == 3 || slot == 4) TileEntityLiquidXPConverter.this.rfPerTickUsage = (int)Math.max(TileEntityLiquidXPConverter.this.rfPerTickBaseUsage, (TileEntityLiquidXPConverter.this.rfPerTickBaseUsage * Math.max(1, (Math.pow(TileEntityLiquidXPConverter.this.speedUpgradeModifier, 1.1f) - Math.pow(TileEntityLiquidXPConverter.this.efficiencyUpgradeModifier, 0.9f)))));
			TileEntityLiquidXPConverter.this.markDirty();
		};
	};
	
	protected FluidTank fluidTank = new FluidTank(new FluidStack(ModFluids.LIQUID_XP, 0), 64000) {
		@Override
		protected void onContentsChanged() {
			super.onContentsChanged();
			TileEntityLiquidXPConverter.this.markDirty();
		};
	};
	
	public TileEntityLiquidXPConverter() {
		super("Liquid XP converter", 5000000, 2048, 0);
		rfPerTickBaseUsage = 100;
		rfPerTickUsage = 100;
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setTag("FluidTank", this.fluidTank.writeToNBT(new NBTTagCompound()));
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.fluidTank.readFromNBT(compound.getCompoundTag("FluidTank"));
	}
	
	public int getFluidAmountStored() {
		return this.fluidTank.getFluidAmount();
	}
	
	public int getMaxFluidAmountStored() {
		return this.fluidTank.getCapacity();
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.inventory;
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return (T) this.fluidTank;
		return super.getCapability(capability, facing);
	}
	
	private void updateSpeedModifier(int slot) {
		ItemStack itemStack = this.inventory.getStackInSlot(slot);
		if (!itemStack.isEmpty()) {
			int speedModifier = EnumUpgrade.byUpgradeDamage(itemStack.getMetadata()).getUpgradeValueSpeed();
			speedUpgradeModifier = speedModifier;
			currentSpeedUpgrade = itemStack;
		} else if (itemStack.isEmpty() && currentSpeedUpgrade != null) {
			currentSpeedUpgrade = null;
			speedUpgradeModifier = 0;
		}
		progressSpeed = (int)Math.max(1, Math.pow(this.speedUpgradeModifier, 2));
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
