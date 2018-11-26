package com.hqbanana.endgamestuffmod.containers.machines;

import com.hqbanana.endgamestuffmod.containers.ContainerBase;
import com.hqbanana.endgamestuffmod.containers.slots.SlotSpecific;
import com.hqbanana.endgamestuffmod.containers.slots.SlotUpgradeEfficiency;
import com.hqbanana.endgamestuffmod.containers.slots.SlotUpgradeSpeed;
import com.hqbanana.endgamestuffmod.init.ModItems;
import com.hqbanana.endgamestuffmod.tileentities.machines.TileEntityDragonBreathFactory;
import com.hqbanana.endgamestuffmod.tileentities.machines.TileEntityLiquidXPConverter;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerLiquidXPConverter extends ContainerBase {
	IItemHandler handler = null;
	
	public ContainerLiquidXPConverter(InventoryPlayer playerInv, TileEntityLiquidXPConverter te) {
		super(playerInv, te);
		handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		this.addSlotToContainer(new SlotSpecific(handler, 0, 125, 37, new ItemStack(ModItems.XP_CHUNK)));
		this.addSlotToContainer(new SlotUpgradeSpeed(handler, 1, 176, 4));
		this.addSlotToContainer(new SlotUpgradeEfficiency(handler, 2, 176, 24));
	}
}
