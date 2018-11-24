package com.hqbanana.endgamestuffmod.containers.machines;

import com.hqbanana.endgamestuffmod.containers.ContainerBase;
import com.hqbanana.endgamestuffmod.containers.slots.SlotSpecific;
import com.hqbanana.endgamestuffmod.containers.slots.SlotUpgradeEfficiency;
import com.hqbanana.endgamestuffmod.containers.slots.SlotUpgradeSpeed;
import com.hqbanana.endgamestuffmod.containers.slots.SlotWitherFactoryOutput;
import com.hqbanana.endgamestuffmod.tileentities.machines.TileEntityWitherFactory;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerWitherFactory extends ContainerBase {
	IItemHandler handler = null;
	
	public ContainerWitherFactory(InventoryPlayer playerInv, TileEntityWitherFactory te) {
		super(playerInv, te);
		handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		this.addSlotToContainer(new SlotSpecific(handler, 0, 62, 58, new ItemStack(Blocks.SOUL_SAND)));
		this.addSlotToContainer(new SlotSpecific(handler, 1, 44, 40, new ItemStack(Blocks.SOUL_SAND)));
		this.addSlotToContainer(new SlotSpecific(handler, 2, 62, 40, new ItemStack(Blocks.SOUL_SAND)));
		this.addSlotToContainer(new SlotSpecific(handler, 3, 80, 40, new ItemStack(Blocks.SOUL_SAND)));
		this.addSlotToContainer(new SlotSpecific(handler, 4, 44, 22, new ItemStack(Items.SKULL, 1)));
		this.addSlotToContainer(new SlotSpecific(handler, 5, 62, 22, new ItemStack(Items.SKULL, 1)));
		this.addSlotToContainer(new SlotSpecific(handler, 6, 80, 22, new ItemStack(Items.SKULL, 1)));
		this.addSlotToContainer(new SlotWitherFactoryOutput(handler, 7, 125, 40));
		this.addSlotToContainer(new SlotWitherFactoryOutput(handler, 8, 125, 58));
		this.addSlotToContainer(new SlotWitherFactoryOutput(handler, 9, 125, 22));
		this.addSlotToContainer(new SlotUpgradeSpeed(handler, 10, 176, 4));
		this.addSlotToContainer(new SlotUpgradeEfficiency(handler, 11, 176, 24));
	}
}
