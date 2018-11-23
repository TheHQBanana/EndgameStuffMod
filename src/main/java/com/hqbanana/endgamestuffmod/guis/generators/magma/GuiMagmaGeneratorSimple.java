package com.hqbanana.endgamestuffmod.guis.generators.magma;

import com.hqbanana.endgamestuffmod.containers.generators.magma.ContainerMagmaGeneratorSimple;
import com.hqbanana.endgamestuffmod.tileentities.generators.magma.TileEntityMagmaGeneratorSimple;

import net.minecraft.entity.player.InventoryPlayer;

public class GuiMagmaGeneratorSimple extends GuiMagmaGeneratorBase {
	public GuiMagmaGeneratorSimple(InventoryPlayer player, TileEntityMagmaGeneratorSimple te) {
		super(player, te, new ContainerMagmaGeneratorSimple(player, te), "magma/magma_generator_simple");
	}
}
