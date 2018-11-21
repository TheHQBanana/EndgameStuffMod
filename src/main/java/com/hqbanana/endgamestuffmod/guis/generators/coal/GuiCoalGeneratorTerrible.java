package com.hqbanana.endgamestuffmod.guis.generators.coal;

import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorTerrible;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorTerrible;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCoalGeneratorTerrible extends GuiCoalGeneratorBase {
	public GuiCoalGeneratorTerrible(InventoryPlayer player, TileEntityCoalGeneratorTerrible te) {
		super(player, te, new ContainerCoalGeneratorTerrible(player, te), "coal/coal_generator_terrible");
	}
}
