package com.hqbanana.endgamestuffmod.tileentities;

import javax.annotation.Nullable;

import com.hqbanana.endgamestuffmod.blocks.BlockRubyFurnace;
import com.hqbanana.endgamestuffmod.blocks.BlockRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.recipes.RubyFurnaceRecipes;
import com.hqbanana.endgamestuffmod.util.CustomEnergyStorage;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityRubyFurnaceElectric extends TileEntity implements ITickable {
	private ItemStackHandler handler = new ItemStackHandler(3) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			TileEntityRubyFurnaceElectric.this.markDirty();
		};
	};
	private int minEnergyToOperate = 20, maxEnergyStored = 100000;
	
	private CustomEnergyStorage energyStorage = new CustomEnergyStorage(maxEnergyStored, 100, 100, 100000);
	
	private String customName;
	private ItemStack smelting = ItemStack.EMPTY;
	
	private int tick, cookTime, energyStored = energyStorage.getEnergyStored();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		if (capability == CapabilityEnergy.ENERGY) return true;
		else return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		if (capability == CapabilityEnergy.ENERGY) return (T) this.energyStorage; 
		return super.getCapability(capability, facing);
	}
	
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	private void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, getState(), getState(), 3);
		world.scheduleBlockUpdate(pos,this.getBlockType(),0,0);
		markDirty();
	}
	
	private IBlockState getState() {
		return world.getBlockState(pos);
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.ruby_furnace_electric");
	}
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 1, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		handleUpdateTag(pkt.getNbtCompound());
		readFromNBT(pkt.getNbtCompound());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.cookTime = compound.getInteger("CookTime");
		this.energyStorage.readFromNBT(compound);
		
		if (compound.hasKey("Name", 8)) this.setCustomName(compound.getString("Name"));
	}
	
	//TODO:: Fix forge energy capability usage in GUI!
	//https://mcforge.readthedocs.io/en/latest/tileentities/tileentity/#synchronizing-the-data-to-the-client-
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("Inventory", this.handler.serializeNBT());
		compound.setInteger("CookTime", (short)this.cookTime);
		this.energyStorage.writeToNBT(compound);
		
		if (this.hasCustomName()) compound.setString("Name", this.customName);
		return compound;
	}
	
	public void update() {
		tick++;
		tick = tick > 20 ? 0 : tick;
		
		//if (tick == 0) System.out.println("Energy: " + Integer.toString(energyStored));
		if (!this.world.isRemote) {
			 ItemStack[] inputs = new ItemStack[] { handler.getStackInSlot(0), handler.getStackInSlot(1) };
				//System.out.println("ITEMS: " + inputs[0] + ", " + inputs[1]);
			if (energyStorage.getEnergyStored() >= minEnergyToOperate) {
				if (cookTime > 0) {
					energyStorage.extractEnergy(minEnergyToOperate, false);
					setBlockToUpdate();
					//System.out.println("UPDATING BLOCK!");
					//energyStored -= minEnergyToOperate;
					cookTime++;
					BlockRubyFurnaceElectric.setState(true, world, pos);
					if (cookTime == 100) {
						if (handler.getStackInSlot(2).getCount() > 0) handler.getStackInSlot(2).grow(smelting.getCount());
						else handler.insertItem(2, smelting, false);
						smelting = ItemStack.EMPTY;
						cookTime = 0;
						return;
					}
				} else {
					if (!inputs[0].isEmpty() && !inputs[1].isEmpty()) {
						ItemStack output = RubyFurnaceRecipes.getInstance().getRubyResult(inputs[0], inputs[1]);
						if (!output.isEmpty()) {
							System.out.println("STARTING SMELT");
							smelting = output;
							cookTime++;
							inputs[0].shrink(1);
							inputs[1].shrink(1);
							handler.setStackInSlot(0, inputs[0]);
							handler.setStackInSlot(1, inputs[1]);
							energyStorage.extractEnergy(minEnergyToOperate, false);
							//energyStored -= minEnergyToOperate;
							setBlockToUpdate();
						}
					}
				}
			}
		}
	}
	
	private void setBlockToUpdate() {
		sendUpdates();
		//System.out.println("UPDATING...!");
		//shouldUpdate = true;
	}
	
	public int getEnergyStored() {
		//setBlockToUpdate();
		//System.out.println("Stored: " + this.energyStorage.getEnergyStored());
		return this.energyStorage.getEnergyStored();
	}
	
	public int getMaxEnergyStored() {
		//System.out.println("Stored: " + this.energyStorage.getMaxEnergyStored());
		return this.energyStorage.getMaxEnergyStored();
	}
	
	private boolean canSmelt() {
		if(((ItemStack)this.handler.getStackInSlot(0)).isEmpty() || ((ItemStack)this.handler.getStackInSlot(1)).isEmpty()) return false;
		else {
			ItemStack result = RubyFurnaceRecipes.getInstance().getRubyResult((ItemStack)this.handler.getStackInSlot(0), (ItemStack)this.handler.getStackInSlot(1));	
			if(result.isEmpty()) return false;
			else {
				ItemStack output = (ItemStack)this.handler.getStackInSlot(3);
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
            ItemStack outputStack = this.handler.getStackInSlot(3);

            if (outputStack.isEmpty()) {
            	this.handler.setStackInSlot(3, outputRecipe.copy());
            } else if (outputRecipe.getItem() == outputStack.getItem()) {
            	outputStack.grow(outputRecipe.getCount());
            }

            itemstack.shrink(1);
            itemstack1.shrink(1);
        }
	}
	
	public static int getItemBurnTime(ItemStack fuel) {
		if (fuel.isEmpty()) return 0;
		else {
			Item item = fuel.getItem();
			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR) {
				Block block = Block.getBlockFromItem(item);
				if (block == Blocks.WOODEN_SLAB) return 150;
				if (block.getDefaultState().getMaterial() == Material.WOOD) return 300;
				if (block == Blocks.COAL_BLOCK) return 16000;
				if (block == Blocks.DIAMOND_BLOCK) return 100000;
			}
			
			if (item == Items.STICK) return 400;
			if (item == Items.COAL) return 1600;
			if (item == Items.LAVA_BUCKET) return 20000;
			if (item == Item.getItemFromBlock(Blocks.SAPLING)) return 100;
			if (item == Items.BLAZE_ROD) return 2400;
			
			return GameRegistry.getFuelValue(fuel);
		}
	}
	
	public static boolean isItemFuel (ItemStack fuel) {
		return getItemBurnTime(fuel) > 0;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64;
 	}
	
	public String getGuiId() {
		return "egsm:ruby_furnace_electric";
	}
	
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.cookTime;
		case 1:
			return this.energyStorage.getEnergyStored();
		case 2:
			return this.energyStorage.getMaxEnergyStored();
		default:
			return 0;
		}
	}
	
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.cookTime = value;
			break;
		case 1:
			this.energyStored = value;
			break;
		case 2:
			this.maxEnergyStored = value;
			break;
		}
	}
}
