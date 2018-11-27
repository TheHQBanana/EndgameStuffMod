package com.hqbanana.endgamestuffmod.tileentities.machines;

import com.hqbanana.endgamestuffmod.power.EnergyStorageBase;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityMachineBase extends EnergyStorageBase implements ITickable {
	protected int currentProgressTime, totalProgressTime, rfPerTickBaseUsage = 100, rfPerTickUsage = 100, progressSpeed = 1, baseProcessTime = 20;
	protected int speedUpgradeModifier = 0, efficiencyUpgradeModifier = 0;
	protected ItemStack currentSpeedUpgrade = null, currentEfficiencyUpgrade = null;
	
	public TileEntityMachineBase(String name, int maxPower, int maxIn, int maxOut) {
		super(name, maxPower, maxIn, maxOut);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setInteger("CurrentProgressTime", this.currentProgressTime);
		compound.setInteger("TotalProgressTime", this.totalProgressTime);
		compound.setInteger("SpeedUpgradeModifier", this.speedUpgradeModifier);
		compound.setInteger("EfficiencyUpgradeModifier", this.efficiencyUpgradeModifier);
		compound.setInteger("RFPerTickBaseUsage", this.rfPerTickBaseUsage);
		compound.setInteger("RFPerTickUsage", this.rfPerTickUsage);
		compound.setInteger("ProgressSpeed", this.progressSpeed);
		compound.setInteger("BaseProcessTime", this.baseProcessTime);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.currentProgressTime = compound.getInteger("CurrentProgressTime");
		this.totalProgressTime = compound.getInteger("TotalProgressTime");
		this.speedUpgradeModifier = compound.getInteger("SpeedUpgradeModifier");
		this.efficiencyUpgradeModifier = compound.getInteger("EfficiencyUpgradeModifier");
		this.rfPerTickUsage = compound.getInteger("RFPerTickUsage");
		this.progressSpeed = compound.getInteger("ProgressSpeed");
		this.rfPerTickBaseUsage = compound.getInteger("RFPerTickBaseUsage");
		this.baseProcessTime = compound.getInteger("BaseProcessTime");
	}
	
	public int getCurrentProgressTime() {
		return this.currentProgressTime % this.baseProcessTime;
	}
	
	public int getTotalProgressTime() {
		return this.totalProgressTime;
	}
	
	@Override
	public void update() {
		
	}
}
