package com.hqbanana.endgamestuffmod.guis.generators.coal;

import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorSimple;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorSimple;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCoalGeneratorSimple extends GuiCoalGeneratorBase {
	public GuiCoalGeneratorSimple(InventoryPlayer player, TileEntityCoalGeneratorSimple te) {
		super(player, te, new ContainerCoalGeneratorSimple(player, te), "coal/coal_generator_simple");
	}
}
