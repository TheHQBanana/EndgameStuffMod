package com.hqbanana.endgamestuffmod.containers.machines;

import com.hqbanana.endgamestuffmod.containers.ContainerBase;
import com.hqbanana.endgamestuffmod.containers.slots.SlotSpecific;
import com.hqbanana.endgamestuffmod.containers.slots.SlotUpgradeEfficiency;
import com.hqbanana.endgamestuffmod.containers.slots.SlotUpgradeSpeed;
import com.hqbanana.endgamestuffmod.tileentities.machines.TileEntityDragonBreathFactory;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerDragonBreathFactory extends ContainerBase {
	IItemHandler handler = null;
	
	public ContainerDragonBreathFactory(InventoryPlayer playerInv, TileEntityDragonBreathFactory te) {
		super(playerInv, te);
		handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		this.addSlotToContainer(new SlotSpecific(handler, 0, 44, 40, new ItemStack(Item.getItemFromBlock(Blocks.DRAGON_EGG)), 1));
		this.addSlotToContainer(new SlotSpecific(handler, 1, 79, 40, new ItemStack(Items.END_CRYSTAL)));
		this.addSlotToContainer(new SlotSpecific(handler, 2, 125, 40, new ItemStack(Items.DRAGON_BREATH)));
		this.addSlotToContainer(new SlotUpgradeSpeed(handler, 3, 176, 4));
		this.addSlotToContainer(new SlotUpgradeEfficiency(handler, 4, 176, 24));
	}
}
