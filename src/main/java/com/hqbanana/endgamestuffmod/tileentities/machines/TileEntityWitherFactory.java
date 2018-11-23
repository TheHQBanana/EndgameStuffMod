package com.hqbanana.endgamestuffmod.tileentities.machines;

import com.hqbanana.endgamestuffmod.blocks.materials.BlockNetherStar;
import com.hqbanana.endgamestuffmod.power.EnergyStorageBase;
import com.hqbanana.endgamestuffmod.tileentities.generators.magma.TileEntityMagmaGeneratorAdvanced;
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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityWitherFactory extends EnergyStorageBase implements ITickable {
	protected ItemStackHandler inventory = new ItemStackHandler(12) {
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
			TileEntityWitherFactory.this.markDirty();
		};
	};
	
	private int currentProgressTime, totalProgressTime, rfPerTickUsage = 2500, progressSpeed = 1;
	private int speedUpgradeModifier = 0, efficiencyUpgradeModifier = 0;
	private ItemStack currentSpeedUpgrade = null, currentEfficiencyUpgrade = null;
	
	public TileEntityWitherFactory() {
		super("Wither factory", 50000000, 100000, 0);
	}

	@Override
	public void update() {
		if (!this.world.isRemote) {
			Block block = this.world.getBlockState(this.pos.down()).getBlock();
			if (block instanceof BlockNetherStar) { //Change this to Netherest star block when that's created!
				int progressTime = getCanProgressWithTime();
				if (totalProgressTime == 0 && progressTime > 0 && outputItems(true)) { //TEST OUPUTTING!
					totalProgressTime = progressTime;
					takeItems();
				}
				if (this.getEnergyStored() >= rfPerTickUsage && currentProgressTime < totalProgressTime) {
					this.internalExtractEnergy(rfPerTickUsage, false);
					System.out.println("Speed: " + progressSpeed);
					currentProgressTime += progressSpeed;
				} else if (currentProgressTime >= totalProgressTime && currentProgressTime != 0 && totalProgressTime != 0) {
					currentProgressTime = 0;
					totalProgressTime = 0;
					outputItems(false);
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
	
	private boolean outputItems(boolean simulate) {
		for (int i = 7; i < 10; i++) {
			ItemStack itemStack = this.inventory.getStackInSlot(i);
			if (itemStack.isEmpty()) {
				if (!simulate) this.inventory.setStackInSlot(i, new ItemStack(Items.NETHER_STAR, 1));
				return true;
			} else if (itemStack.getItem() == Items.NETHER_STAR && itemStack.getCount() < itemStack.getItem().getItemStackLimit()) {
				if (!simulate) this.inventory.getStackInSlot(i).grow(1);
				return true;
			}
		}
		return false;
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
		return 4000; //Add modifiers to this later
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
		compound.setInteger("RFPerTickUsage", this.rfPerTickUsage);
		compound.setInteger("ProgressSpeed", this.progressSpeed);
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
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.inventory;
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
		//rfPerTickUsage = (int)Math.max(rfPerTickUsage, (rfPerTickUsage * Math.max(1, (Math.pow(speedUpgradeModifier, 0.5f) - Math.pow(this.efficiencyUpgradeModifier, 0.5)))));
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
		//rfPerTickUsage = (int)Math.max(rfPerTickUsage, (rfPerTickUsage * Math.max(1, (Math.pow(speedUpgradeModifier, 0.5f) - Math.pow(this.efficiencyUpgradeModifier, 0.5)))));
		progressSpeed = (int)Math.max(1, Math.pow(this.speedUpgradeModifier, 2));
		
		//TEST WHAT THE FUCK IS GOING ON HERE... upgrades are acting weird, progressspeed not going correctly, neither is rfPerTickUsage
	}
}
