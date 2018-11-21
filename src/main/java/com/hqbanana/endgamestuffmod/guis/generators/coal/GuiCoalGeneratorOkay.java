package com.hqbanana.endgamestuffmod.guis.generators.coal;

import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorOkay;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorOkay;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCoalGeneratorOkay extends GuiCoalGeneratorBase {
	public GuiCoalGeneratorOkay(InventoryPlayer player, TileEntityCoalGeneratorOkay te) {
		super(player, te, new ContainerCoalGeneratorOkay(player, te), "coal/coal_generator");
	}
}
