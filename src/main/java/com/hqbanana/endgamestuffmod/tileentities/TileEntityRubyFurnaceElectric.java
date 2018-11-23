package com.hqbanana.endgamestuffmod.tileentities;

import com.hqbanana.endgamestuffmod.power.EnergyStorageBase;
import com.hqbanana.endgamestuffmod.recipes.RubyFurnaceRecipes;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityRubyFurnaceElectric extends EnergyStorageBase implements ITickable {
	private ItemStackHandler handler = new ItemStackHandler(3) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			TileEntityRubyFurnaceElectric.this.markDirty();
		};
	};
	
	public TileEntityRubyFurnaceElectric() {
		super("rubyFurnaceElectric", 100000, 100, 0, 0);
	}
	
	private int minEnergyToOperate = 20;
	private int tick, cookTime;
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	private IBlockState getState() {
		return world.getBlockState(pos);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.cookTime = compound.getInteger("CookTime");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound = super.writeToNBT(compound);
		compound.setTag("Inventory", this.handler.serializeNBT());
		compound.setInteger("CookTime", (short)this.cookTime);
		return compound;
	}
	
	@Override
	public void update() {
		tick++;
		tick = tick > 20 ? 0 : tick;
		
		if (!this.world.isRemote) {
			 boolean canSmelt = canSmelt();
			 if (cookTime < 100 && getEnergyStored() >= minEnergyToOperate && canSmelt) {
				 internalExtractEnergy(minEnergyToOperate, false);
				 cookTime++;
			 } else if (!canSmelt) cookTime = 0;
			 if (cookTime >= 100 && canSmelt) {
				 smeltItem();
				 cookTime = 0;
			 }
			 this.markDirty();
		}
	}
	
	private boolean canSmelt() {
		if(((ItemStack)this.handler.getStackInSlot(0)).isEmpty() || ((ItemStack)this.handler.getStackInSlot(1)).isEmpty()) return false;
		else {
			ItemStack result = RubyFurnaceRecipes.getInstance().getRubyResult((ItemStack)this.handler.getStackInSlot(0), (ItemStack)this.handler.getStackInSlot(1));	
			if(result.isEmpty()) return false;
			else {
				ItemStack output = (ItemStack)this.handler.getStackInSlot(2);
				if(output.isEmpty()) return true;
				if(!output.isItemEqual(result)) return false;
				int res = output.getCount() + result.getCount();
				return res <= 64 && res <= output.getMaxStackSize();
			}
		}
	}
	
	private void smeltItem() {
		if (this.canSmelt()) {
            ItemStack itemstack = this.handler.getStackInSlot(0);
            ItemStack itemstack1 = this.handler.getStackInSlot(1);
            ItemStack outputRecipe = RubyFurnaceRecipes.getInstance().getRubyResult(itemstack, itemstack1);
            ItemStack outputStack = this.handler.getStackInSlot(2);

            if (outputStack.isEmpty()) {
            	this.handler.setStackInSlot(2, outputRecipe.copy());
            } else if (outputRecipe.getItem() == outputStack.getItem()) {
            	outputStack.grow(outputRecipe.getCount());
            }

            itemstack.shrink(1);
            itemstack1.shrink(1);
        }
	}
	
	public void dropInventory() {
		for (int i = 0; i < this.handler.getSlots(); ++i) {
			ItemStack itemStack = this.handler.getStackInSlot(i);
			if (!itemStack.isEmpty()) InventoryHelper.spawnItemStack(getWorld(), pos.getX(), pos.getY(), pos.getZ(), itemStack);
		}
	}
	
	public String getGuiId() {
		return "egsm:ruby_furnace_electric";
	}
	
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.cookTime;
		default:
			return 0;
		}
	}
	
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.cookTime = value;
			break;
		}
	}
}
