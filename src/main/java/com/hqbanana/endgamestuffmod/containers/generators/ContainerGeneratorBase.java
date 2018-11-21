package com.hqbanana.endgamestuffmod.containers.generators;

import com.hqbanana.endgamestuffmod.containers.ContainerBase;
import com.hqbanana.endgamestuffmod.tileentities.generators.TileEntityGeneratorBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerGeneratorBase extends ContainerBase {
	public ContainerGeneratorBase(InventoryPlayer player, TileEntityGeneratorBase te) {
		super(player, te);
	}
}
