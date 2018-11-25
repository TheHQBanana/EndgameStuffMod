package com.hqbanana.endgamestuffmod.tileentities.machines;

import com.hqbanana.endgamestuffmod.blocks.materials.BlockNetherestStar;
import com.hqbanana.endgamestuffmod.init.ModFluids;
import com.hqbanana.endgamestuffmod.inventories.InventoryWitherFactory;
import com.hqbanana.endgamestuffmod.power.EnergyStorageBase;
import com.hqbanana.endgamestuffmod.util.EnumUpgrade;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
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

public class TileEntityWitherFactory extends EnergyStorageBase implements ITickable {
	protected InventoryWitherFactory inventory = new InventoryWitherFactory(12) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			switch(slot) {
			case 10:
				TileEntityWitherFactory.this.updateSpeedModifier(slot);
				break;
			case 11: 
				TileEntityWitherFactory.this.updateEfficiencyModifier(slot);
				break;
			}
			if (slot == 10 || slot == 11) TileEntityWitherFactory.this.rfPerTickUsage = (int)Math.max(TileEntityWitherFactory.this.rfPerTickBaseUsage, (TileEntityWitherFactory.this.rfPerTickBaseUsage * Math.max(1, (Math.pow(TileEntityWitherFactory.this.speedUpgradeModifier, 1.1f) - Math.pow(TileEntityWitherFactory.this.efficiencyUpgradeModifier, 0.9f)))));
			TileEntityWitherFactory.this.markDirty();
		};
	};
	
	protected FluidTank fluidTank = new FluidTank(new FluidStack(ModFluids.LIQUID_XP, 0), 64000) {
		@Override
		protected void onContentsChanged() {
			super.onContentsChanged();
			TileEntityWitherFactory.this.markDirty();
		};
	};
	
	private int currentProgressTime, totalProgressTime, rfPerTickBaseUsage = 2500, rfPerTickUsage = 2500, progressSpeed = 1;
	private int speedUpgradeModifier = 0, efficiencyUpgradeModifier = 0;
	private ItemStack currentSpeedUpgrade = null, currentEfficiencyUpgrade = null;
	
	public TileEntityWitherFactory() {
		super("Wither factory", 20000000, 100000, 0);
	}

	@Override
	public void update() {
		if (!this.world.isRemote) {
			Block block = this.world.getBlockState(this.pos.down()).getBlock();
			if (block instanceof BlockNetherestStar) { //Change this to Netherest star block when that's created!
				int progressTime = getCanProgressWithTime();
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
		for (int i = 0; i < 7; i++) {
			this.inventory.getStackInSlot(i).shrink(1);
		}
	}
	
	private boolean outputItemsAndXP(boolean simulate) {
		boolean success = false;
		for (int i = 7; i < 10; i++) {
			ItemStack itemStack = this.inventory.getStackInSlot(i);
			if (itemStack.isEmpty()) {
				if (!simulate) this.inventory.setStackInSlot(i, new ItemStack(Items.NETHER_STAR, 1));
				success = true;
			} else if (itemStack.getItem() == Items.NETHER_STAR && itemStack.getCount() < itemStack.getItem().getItemStackLimit()) {
				if (!simulate) this.inventory.getStackInSlot(i).grow(1);
				success = true;
			}
		}
		if (!simulate) this.fluidTank.fillInternal(new FluidStack(ModFluids.LIQUID_XP, 1000), true);
		success = this.fluidTank.getFluidAmount() + 1000 < this.fluidTank.getCapacity();
		return success;
	}
	
	private int getCanProgressWithTime() {
		for (int i = 0; i < 7; i++ ) {
			ItemStack itemStack = this.inventory.getStackInSlot(i);
			if (itemStack.isEmpty()) return 0;
			if (i >= 0 && i < 4) {
				if (!(itemStack.getItem() instanceof ItemBlock)) return 0;
				Block block = Block.getBlockFromItem(itemStack.getItem());
				if (block != Blocks.SOUL_SAND) return 0;
			} else {
				if (itemStack.getItem() != Items.SKULL || itemStack.getItemDamage() != 1) return 0;
			}
		}
		return 4000;
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
