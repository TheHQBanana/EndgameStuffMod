package com.hqbanana.endgamestuffmod.guis.generators.coal;

import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorEfficient;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorEfficient;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCoalGeneratorEfficient extends GuiCoalGeneratorBase {
	public GuiCoalGeneratorEfficient(InventoryPlayer player, TileEntityCoalGeneratorEfficient te) {
		super(player, te, new ContainerCoalGeneratorEfficient(player, te), "coal/coal_generator");
	}
}
