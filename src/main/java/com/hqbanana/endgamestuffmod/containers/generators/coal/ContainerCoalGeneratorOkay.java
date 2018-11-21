package com.hqbanana.endgamestuffmod.containers.generators.coal;

import com.hqbanana.endgamestuffmod.containers.ContainerBase;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorTerrible;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCoalGeneratorOkay extends ContainerBase {
	public ContainerCoalGeneratorOkay(InventoryPlayer player, TileEntityCoalGeneratorTerrible te) {
		super(player, te);
		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 80, 47));
	}
}
