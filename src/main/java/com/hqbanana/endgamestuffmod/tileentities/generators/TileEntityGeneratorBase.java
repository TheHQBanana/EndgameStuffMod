package com.hqbanana.endgamestuffmod.tileentities.generators;

import com.hqbanana.endgamestuffmod.power.CustomEnergyStorage;
import com.hqbanana.endgamestuffmod.power.PowerTransmitter;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityGeneratorBase extends CustomEnergyStorage {
	public TileEntityGeneratorBase(String name, int maxPower, int maxIn, int maxOut, int energy) {
		super(name, maxPower, maxIn, maxOut, energy);
	}
	
	protected PowerTransmitter transmitter;
	
	protected int rfPerTick = 20, totalBurnTime, currentBurnTime;
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.rfPerTick = compound.getInteger("RFPerTick");
		this.totalBurnTime = compound.getInteger("TotalBurnTime");
		this.currentBurnTime = compound.getInteger("CurrentBurnTime");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setInteger("RFPerTick", this.rfPerTick);
		compound.setInteger("TotalBurnTime", this.totalBurnTime);
		compound.setInteger("CurrentBurnTime", (short)this.currentBurnTime);
		return compound;
	}
	
	protected boolean transmitEnergy() {
		if (transmitter == null) transmitter = new PowerTransmitter(this.pos);
		transmitter.transmitEnergy(world, this);
		return true;
	}
	
	public int getRFPerTick() {
		return this.rfPerTick;
	}
	
	public int getCurrentBurnTime() {
		return this.currentBurnTime;
	}
	
	public int getTotalBurnTime() {
		return this.totalBurnTime;
	}
	
	protected void burnFuel() {
		
	}
}
