package com.hqbanana.endgamestuffmod.tileentities.machines;

import com.hqbanana.endgamestuffmod.blocks.materials.BlockNetherestStar;
import com.hqbanana.endgamestuffmod.init.ModFluids;
import com.hqbanana.endgamestuffmod.inventories.InventoryDragonBreathFactory;
import com.hqbanana.endgamestuffmod.power.EnergyStorageBase;
import com.hqbanana.endgamestuffmod.util.EnumUpgrade;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityDragonBreathFactory extends TileEntityMachineBase implements ITickable {
	protected InventoryDragonBreathFactory inventory = new InventoryDragonBreathFactory(5) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			switch(slot) {
			case 3:
				TileEntityDragonBreathFactory.this.updateSpeedModifier(slot);
				break;
			case 4: 
				TileEntityDragonBreathFactory.this.updateEfficiencyModifier(slot);
				break;
			}
			if (slot == 3 || slot == 4) TileEntityDragonBreathFactory.this.rfPerTickUsage = (int)Math.max(TileEntityDragonBreathFactory.this.rfPerTickBaseUsage, (TileEntityDragonBreathFactory.this.rfPerTickBaseUsage * Math.max(1, (Math.pow(TileEntityDragonBreathFactory.this.speedUpgradeModifier, 1.1f) - Math.pow(TileEntityDragonBreathFactory.this.efficiencyUpgradeModifier, 0.9f)))));
			TileEntityDragonBreathFactory.this.markDirty();
		};
	};
	
	protected FluidTank fluidTank = new FluidTank(new FluidStack(ModFluids.LIQUID_XP, 0), 64000) {
		@Override
		protected void onContentsChanged() {
			super.onContentsChanged();
			TileEntityDragonBreathFactory.this.markDirty();
		};
	};
	
	public TileEntityDragonBreathFactory() {
		super("Dragonbreath factory", 20000000, 100000, 0);
		rfPerTickBaseUsage = 2500;
		rfPerTickUsage = 2500;
	}

	@Override
	public void update() {
		//if (totalProgressTime == 0 && getCanProgressWithTime() > 0 && outputItemsAndXP(true)) BlockDragonBreathFactory.setState(true, world, pos);
		//if (currentProgressTime >= totalProgressTime && currentProgressTime != 0 && totalProgressTime != 0) BlockDragonBreathFactory.setState(false, world, pos);
		
		if (!this.world.isRemote) {
			Block block = this.world.getBlockState(this.pos.down()).getBlock();
			if (block instanceof BlockNetherestStar) { //Change this to Netherest star block when that's created!
				int progressTime = getCanProgressWithTime();
				//System.out.println("Can progress: " + progressTime + "Output: " + outputItemsAndXP(true));
				if (totalProgressTime == 0 && progressTime > 0 && outputItemsAndXP(true)) { //TEST OUPUTTING!
					totalProgressTime = progressTime;
					takeItems();
				}
				if (this.getEnergyStored() >= rfPerTickUsage && currentProgressTime < totalProgressTime) {
					this.internalExtractEnergy(rfPerTickUsage, false);
					currentProgressTime += progressSpeed;
				} else if (currentProgressTime >= totalProgressTime && currentProgressTime != 0 && totalProgressTime != 0) {
					currentProgressTime = 0;
					totalProgressTime = 0;
					outputItemsAndXP(false);
				}
			}
			this.markDirty();
		}
	}
	
	private void takeItems() {
		//Do not take out the item in slot 0, as this is the catalyst and thus will not be consumed!
		this.inventory.getStackInSlot(1).shrink(1);
	}
	
	private boolean outputItemsAndXP(boolean simulate) {
		boolean success = false;
		
		ItemStack itemStack = this.inventory.getStackInSlot(2);
		if (itemStack.isEmpty()) {
			if (!simulate) this.inventory.setStackInSlot(2, new ItemStack(Items.DRAGON_BREATH, 1));
			success = true;
		} else if (itemStack.getItem() == Items.DRAGON_BREATH && itemStack.getCount() < itemStack.getItem().getItemStackLimit()) {
			if (!simulate) this.inventory.getStackInSlot(2).grow(1);
			success = true;
		}
		if (!simulate) this.fluidTank.fillInternal(new FluidStack(ModFluids.LIQUID_XP, 4000), true);
		success = this.fluidTank.getFluidAmount() + 1000 < this.fluidTank.getCapacity();
		return success;
	}
	
	private int getCanProgressWithTime() {
		ItemStack itemStack = this.inventory.getStackInSlot(0);
		if (itemStack.isEmpty() || itemStack.getItem() != Item.getItemFromBlock(Blocks.DRAGON_EGG)) return 0;
		if (this.inventory.getStackInSlot(1).isEmpty() || this.inventory.getStackInSlot(1).getItem() != Items.END_CRYSTAL) return 0;
		return 40;
	}
	
	public int getCurrentProgressTime() {
		return this.currentProgressTime;
	}
	
	public int getTotalProgressTime() {
		return this.totalProgressTime;
	}
	
	public static boolean isActive() {
		return true;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setTag("Inventory", this.inventory.serializeNBT());
		compound.setInteger("CurrentProgressTime", this.currentProgressTime);
		compound.setInteger("TotalProgressTime", this.totalProgressTime);
		compound.setInteger("SpeedUpgradeModifier", this.speedUpgradeModifier);
		compound.setInteger("EfficiencyUpgradeModifier", this.efficiencyUpgradeModifier);
		compound.setInteger("RFPerTickBaseUsage", this.rfPerTickBaseUsage);
		compound.setInteger("RFPerTickUsage", this.rfPerTickUsage);
		compound.setInteger("ProgressSpeed", this.progressSpeed);
		compound.setTag("FluidTank", this.fluidTank.writeToNBT(new NBTTagCompound()));
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.currentProgressTime = compound.getInteger("CurrentProgressTime");
		this.totalProgressTime = compound.getInteger("TotalProgressTime");
		this.speedUpgradeModifier = compound.getInteger("SpeedUpgradeModifier");
		this.efficiencyUpgradeModifier = compound.getInteger("EfficiencyUpgradeModifier");
		this.rfPerTickUsage = compound.getInteger("RFPerTickUsage");
		this.progressSpeed = compound.getInteger("ProgressSpeed");
		this.rfPerTickBaseUsage = compound.getInteger("RFPerTickBaseUsage");
		this.fluidTank.readFromNBT(compound.getCompoundTag("FluidTank"));
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
	
	public void dropInventory(World world, BlockPos pos) {
		for (int i = 0; i < this.inventory.getSlots(); i++) {
			world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), this.inventory.getStackInSlot(i)));
		}
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
	
	public int getFluidAmountStored() {
		return this.fluidTank.getFluidAmount();
	}
	
	public int getMaxFluidAmountStored() {
		return this.fluidTank.getCapacity();
	}
}
