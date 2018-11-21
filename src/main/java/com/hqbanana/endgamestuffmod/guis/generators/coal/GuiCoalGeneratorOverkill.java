package com.hqbanana.endgamestuffmod.guis.generators.coal;

import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorOverkill;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorOverkill;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCoalGeneratorOverkill extends GuiCoalGeneratorBase {
	public GuiCoalGeneratorOverkill(InventoryPlayer player, TileEntityCoalGeneratorOverkill te) {
		super(player, te, new ContainerCoalGeneratorOverkill(player, te), "coal/coal_generator");
	}
}
