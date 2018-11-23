package com.hqbanana.endgamestuffmod.tileentities.generators.magma;

import com.hqbanana.endgamestuffmod.tileentities.generators.TileEntityGeneratorBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityMagmaGeneratorBase extends TileEntityGeneratorBase {
	protected FluidTank fluidTank = new FluidTank(2000) {
		@Override
		protected void onContentsChanged() {
			super.onContentsChanged();
			TileEntityMagmaGeneratorBase.this.markDirty();
		};
	};
	
	public TileEntityMagmaGeneratorBase(String name, int maxPower, int maxIn, int maxOut, int energy) {
		super(name, maxPower, maxIn, maxOut, energy);
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
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setTag("Inventory", this.inventory.serializeNBT());
		compound.setTag("FluidTank", this.fluidTank.writeToNBT(new NBTTagCompound()));
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.fluidTank.readFromNBT(compound.getCompoundTag("FluidTank"));
	}
	
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
					internalReceiveEnergy(rfPerTick, false);
					currentBurnTime++;
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
	protected void burnFuel() {
		this.fluidTank.drainInternal(1000, true);
	}
	
	public static int getFluidBurnTime(FluidStack fluidStack) {
		if (fluidStack.amount <= 0) return 0;
		else {
			Fluid fluid = fluidStack.getFluid();
			if (fluid == FluidRegistry.LAVA) return 20000;
			return 0;
		}
	}
	
	public void dropInventory(World world, BlockPos pos) {

	}
	
	public int getFluidAmountStored() {
		return this.fluidTank.getFluidAmount();
	}
	
	public int getMaxFluidAmountStored() {
		return this.fluidTank.getCapacity();
	}
}