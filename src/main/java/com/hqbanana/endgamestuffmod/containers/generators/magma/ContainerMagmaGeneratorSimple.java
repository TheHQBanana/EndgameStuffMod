package com.hqbanana.endgamestuffmod.containers.generators.magma;

import com.hqbanana.endgamestuffmod.containers.generators.ContainerGeneratorBase;
import com.hqbanana.endgamestuffmod.tileentities.generators.magma.TileEntityMagmaGeneratorSimple;

import net.minecraft.entity.player.InventoryPlayer;

public class ContainerMagmaGeneratorSimple extends ContainerGeneratorBase {
	public ContainerMagmaGeneratorSimple(InventoryPlayer player, TileEntityMagmaGeneratorSimple te) {
		super(player, te);
	}
}
