package com.hqbanana.endgamestuffmod.tileentities.generators.coal;

import com.hqbanana.endgamestuffmod.tileentities.generators.TileEntityGeneratorBase;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityCoalGeneratorBase extends TileEntityGeneratorBase implements ITickable {
	private ItemStackHandler inventory = new ItemStackHandler(1) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			TileEntityCoalGeneratorBase.this.markDirty();
		};
	};
	
	public TileEntityCoalGeneratorBase(String name, int fuelBurnModifier, int maxPower, int maxIn, int maxOut, int energy) {
		super(name, fuelBurnModifier, maxPower, maxIn, maxOut, energy);
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
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setTag("Inventory", this.inventory.serializeNBT());
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
	}
	
	@Override
	public void update() {
		if (!this.world.isRemote) {
			if (!this.inventory.getStackInSlot(0).isEmpty()) {
				int burnTime = getItemBurnTime(this.inventory.getStackInSlot(0));
				if (totalBurnTime == 0 && burnTime > 0) totalBurnTime = burnTime; 
				if (currentBurnTime < totalBurnTime && this.getEnergyStored() < this.getMaxEnergyStored() && burnTime > 0) {
					internalReceiveEnergy(rfPerTick, false);
					currentBurnTime += fuelBurnModifier;
				} else if (currentBurnTime >= totalBurnTime) {
					burnFuel();
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
		this.inventory.getStackInSlot(0).shrink(1);
	}
	
	public static int getItemBurnTime(ItemStack fuel) {
		if (fuel.isEmpty()) return 0;
		else {
			Item item = fuel.getItem();
			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
				Block block = Block.getBlockFromItem(item);
				if (block == Blocks.COAL_BLOCK) return 16000;
			}
			
			if (item == Items.COAL) return 1600;
			
			return 0;
		}
	}
}
