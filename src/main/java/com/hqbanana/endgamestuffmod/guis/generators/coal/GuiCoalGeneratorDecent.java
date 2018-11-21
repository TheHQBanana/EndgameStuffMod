package com.hqbanana.endgamestuffmod.guis.generators.coal;

import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorDecent;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorDecent;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCoalGeneratorDecent extends GuiCoalGeneratorBase {
	public GuiCoalGeneratorDecent(InventoryPlayer player, TileEntityCoalGeneratorDecent te) {
		super(player, te, new ContainerCoalGeneratorDecent(player, te), "coal/coal_generator");
	}
}
