package com.hqbanana.endgamestuffmod.util;

import com.hqbanana.endgamestuffmod.tileentities.TileEntityBase;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class CustomEnergyStorage extends TileEntityBase implements IEnergyStorage {
	public CustomEnergyStorage(String name, int maxPower, int maxIn, int maxOut) {
		super(name);
		this.maxEnergy = maxPower;
    	this.maxReceive = maxIn;
    	this.maxExtract = maxOut;
    }

	public CustomEnergyStorage(String name, int maxPower, int maxIn, int maxOut, int energy) {
    	super(name);
    	this.maxEnergy = maxPower;
    	this.maxReceive = maxIn;
    	this.maxExtract = maxOut;
    	this.energy = Math.max(0 , Math.min(maxEnergy, energy));
    }
    
    protected int energy;
	protected int maxEnergy;
	protected int maxReceive;
	protected int maxExtract;
    
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
    	int energyReceived = Math.min(maxEnergy - energy, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            energy += energyReceived;
            System.out.println("Energy: " + energy + ", energy received: " + energyReceived);
            this.markDirty();
        }
        return energyReceived;
    }
    
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
    	int energyExtract = (int) Math.min(Math.max(energy, 0), Math.min(this.maxExtract, maxExtract));		
		if (!simulate) {
			energy -= energyExtract;
			this.markDirty();
		}
		return energyExtract;
    }
    
    public int internalExtractEnergy(int extract, boolean simulate) {
		int energyExtract = (int) Math.min(Math.max(energy, 0), extract);
		if (!simulate) {
			System.out.println("Energy before: " + energy + ", energy extract: " + energyExtract);
			energy -= energyExtract;
			System.out.println("Energy after: " + energy);
			this.markDirty();
		}
		return energyExtract;
	}
    
    @Override
    public int getEnergyStored() {
    	return energy;
    }
    
    @Override
    public int getMaxEnergyStored() {
    	return maxEnergy;
    }
    
    @Override
    public boolean canExtract() {
    	return maxExtract != 0;
    }
    
    @Override
    public boolean canReceive() {
    	return maxReceive != 0;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound) {
    	super.readFromNBT(compound);
    	this.energy = compound.getInteger("Energy");
    	this.maxEnergy = compound.getInteger("MaxEnergy");
    	this.maxReceive = compound.getInteger("MaxReceive");
    	this.maxExtract = compound.getInteger("MaxExtract");
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
    	compound = super.writeToNBT(compound);
    	compound.setInteger("Energy", energy);
    	compound.setInteger("MaxEnergy", this.maxEnergy);
    	compound.setInteger("MaxReceive", this.maxReceive);
    	compound.setInteger("MaxExtract", this.maxExtract);
    	return compound;
    }
    
    @Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) return true;
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) return (T) this;
		return super.getCapability(capability, facing);
	}
}
