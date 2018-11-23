package com.hqbanana.endgamestuffmod.containers.generators.magma;

import com.hqbanana.endgamestuffmod.containers.generators.ContainerGeneratorBase;
import com.hqbanana.endgamestuffmod.tileentities.generators.magma.TileEntityMagmaGeneratorBase;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerMagmaGeneratorBase extends ContainerGeneratorBase {
	IItemHandler handler = null;
	
	public ContainerMagmaGeneratorBase(InventoryPlayer player, TileEntityMagmaGeneratorBase te) {
		super(player, te);
		handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
	}
}
