package com.hqbanana.endgamestuffmod.tileentities;

import com.hqbanana.endgamestuffmod.blocks.BlockRubyFurnace;
import com.hqbanana.endgamestuffmod.init.ModBlocks;
import com.hqbanana.endgamestuffmod.recipes.RubyFurnaceRecipes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityRubyFurnace extends TileEntity implements ITickable {
	private ItemStackHandler handler = new ItemStackHandler(4) {
		
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			TileEntityRubyFurnace.this.markDirty();
		};
	};
	
	private String customName;
	private ItemStack smelting = ItemStack.EMPTY;
	
	private int burnTime;
	private int currentBurnTime;
	private int cookTime;
	private int totalCookTime = 200;
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
		else return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.ruby_furnace");
	}
	
	/*@Override
	public ItemStack getStackInSlot(int index) {
		return (ItemStack)this.inventory.get(index);
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);
		
		if (stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());
		if (index == 0 && index + 1 == 1 && !flag) {
			ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);
			this.totalCookTime = this.getCookTime(stack, stack1);
			this.cookTime = 0;
			this.markDirty();
		}
	}*/
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.burnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		//System.out.println("TOTAL COOK TIME: " + compound.getInteger("CookTimeTotal"));
		this.currentBurnTime = getItemBurnTime((ItemStack)this.handler.getStackInSlot(2));
		
		if (compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short)this.burnTime);
		compound.setInteger("CookTime", (short)this.cookTime);
		//System.out.println("TOTAL COOK TIME SAVE: " + (short)this.totalCookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		compound.setTag("Inventory", this.handler.serializeNBT());
		
		if (this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	public boolean isBurning() {
		return this.burnTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(TileEntityRubyFurnace te) {
		return te.getField(0) > 0;
	}
	
	public void update() {
		boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())  --this.burnTime;

        if (!this.world.isRemote) {
            ItemStack itemstack = this.handler.getStackInSlot(2);

            if (this.isBurning() || !itemstack.isEmpty() && !((ItemStack)this.handler.getStackInSlot(2)).isEmpty()) {
                if (!this.isBurning() && this.canSmelt()) {
                    this.burnTime = getItemBurnTime(itemstack);
                    this.currentBurnTime = this.burnTime;

                    if (this.isBurning()) {
                        flag1 = true;

                        if (!itemstack.isEmpty()) {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);

                            if (itemstack.isEmpty()) {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                this.handler.setStackInSlot(2, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt()) {
                    ++this.cookTime;
                    if (this.cookTime == this.totalCookTime) {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.handler.getStackInSlot(0), this.handler.getStackInSlot(1));
                        this.smeltItem();
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.isBurning()) {
                flag1 = true;
                BlockRubyFurnace.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1) {
            this.markDirty();
        }
	}
	
	public int getCookTime(ItemStack input1, ItemStack input2) {
		return 200;
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
		return "egsm:ruby_furnace";
	}
	
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.burnTime;
		case 1:
			return this.currentBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}
	
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.currentBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
			break;
		}
	}
}
