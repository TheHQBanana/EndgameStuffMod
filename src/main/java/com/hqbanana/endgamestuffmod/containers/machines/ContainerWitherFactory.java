package com.hqbanana.endgamestuffmod.containers.machines;

import com.hqbanana.endgamestuffmod.containers.ContainerBase;
import com.hqbanana.endgamestuffmod.containers.slots.SlotSoulSand;
import com.hqbanana.endgamestuffmod.containers.slots.SlotUpgradeEfficiency;
import com.hqbanana.endgamestuffmod.containers.slots.SlotUpgradeSpeed;
import com.hqbanana.endgamestuffmod.containers.slots.SlotWitherSkull;
import com.hqbanana.endgamestuffmod.tileentities.machines.TileEntityWitherFactory;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerWitherFactory extends ContainerBase {
	IItemHandler handler = null;
	
	public ContainerWitherFactory(InventoryPlayer playerInv, TileEntityWitherFactory te) {
		super(playerInv, te);
		handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		this.addSlotToContainer(new SlotSoulSand(handler, 0, 62, 58));
		this.addSlotToContainer(new SlotSoulSand(handler, 1, 44, 40));
		this.addSlotToContainer(new SlotSoulSand(handler, 2, 62, 40));
		this.addSlotToContainer(new SlotSoulSand(handler, 3, 80, 40));
		this.addSlotToContainer(new SlotWitherSkull(handler, 4, 44, 22));
		this.addSlotToContainer(new SlotWitherSkull(handler, 5, 62, 22));
		this.addSlotToContainer(new SlotWitherSkull(handler, 6, 80, 22));
		this.addSlotToContainer(new SlotItemHandler(handler, 7, 125, 40));
		this.addSlotToContainer(new SlotItemHandler(handler, 8, 125, 58));
		this.addSlotToContainer(new SlotItemHandler(handler, 9, 125, 22));
		this.addSlotToContainer(new SlotUpgradeSpeed(handler, 10, 176, 4));
		this.addSlotToContainer(new SlotUpgradeEfficiency(handler, 11, 176, 24));
	}
}
