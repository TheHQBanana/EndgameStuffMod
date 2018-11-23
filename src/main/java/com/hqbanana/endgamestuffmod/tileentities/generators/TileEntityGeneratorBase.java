package com.hqbanana.endgamestuffmod.tileentities.generators;

import com.hqbanana.endgamestuffmod.power.EnergyStorageBase;
import com.hqbanana.endgamestuffmod.power.PowerTransmitter;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorBase;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityGeneratorBase extends EnergyStorageBase implements ITickable {
	protected ItemStackHandler inventory = new ItemStackHandler(2) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			TileEntityGeneratorBase.this.markDirty();
		};
	};
	
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

	@Override
	public void update() {
		
	}
}
