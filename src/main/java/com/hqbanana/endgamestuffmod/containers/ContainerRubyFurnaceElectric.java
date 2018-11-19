package com.hqbanana.endgamestuffmod.containers;

import com.hqbanana.endgamestuffmod.recipes.RubyFurnaceRecipes;
import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnace;
import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnaceElectric;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerRubyFurnaceElectric extends Container {
	private final TileEntityRubyFurnaceElectric te;
	private int cookTime, energyStored, maxEnergyStored;
	
	public ContainerRubyFurnaceElectric(InventoryPlayer player, TileEntityRubyFurnaceElectric te) {
		this.te = te;
		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 40, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 1, 72, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 2, 115, 34));
		
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player, x + (y * 9 + 9), 8 + x * 18, 84 + y * 18));
			}
		}
		
		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
		}
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if (this.cookTime != this.te.getField(0)) listener.sendWindowProperty(this, 0, this.te.getField(0));
			if (this.energyStored != this.te.getField(1)) listener.sendWindowProperty(this, 1, this.te.getField(1));
			if (this.maxEnergyStored != this.te.getField(2)) listener.sendWindowProperty(this, 2, this.te.getField(2));
			
		}
		
		this.cookTime = this.te.getField(0);
		this.energyStored = this.te.getField(1);
		this.maxEnergyStored = this.te.getField(2);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.te.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.te.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			if (index == 2) {
				if (!this.mergeItemStack(stack1, 4, this.inventorySlots.size(), true)) return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			} else if (index != 1 && index != 0) {
				Slot slot0 = this.inventorySlots.get(0);
				Slot slot1 = this.inventorySlots.get(1);
				if (slot0.getStack().isEmpty() || slot0.getStack().getItem().equals(stack1.getItem())) {
					if (RubyFurnaceRecipes.getInstance().isPartOfRecipe(stack1)) {
						if (!this.mergeItemStack(stack1, 0, 1, false)) return ItemStack.EMPTY;
					}
				} else if ((!slot0.getStack().isEmpty() && slot1.getStack().isEmpty()) || slot1.getStack().getItem().equals(stack1.getItem())) {
					if (!RubyFurnaceRecipes.getInstance().getRubyResult(slot0.getStack(), stack1).isEmpty()) {
						if (!this.mergeItemStack(stack1, 1, 2, false)) return ItemStack.EMPTY;
					}
				}
			}
			else if (!this.mergeItemStack(stack1, 3, this.inventorySlots.size(), false)) return ItemStack.EMPTY;
			if (stack1.isEmpty()) slot.putStack(ItemStack.EMPTY);
			else slot.onSlotChanged();
			if (stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}
}
