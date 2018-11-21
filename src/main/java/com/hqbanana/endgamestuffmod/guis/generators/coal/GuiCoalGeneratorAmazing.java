package com.hqbanana.endgamestuffmod.guis.generators.coal;

import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorAmazing;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorAmazing;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCoalGeneratorAmazing  extends GuiCoalGeneratorBase {
	public GuiCoalGeneratorAmazing(InventoryPlayer player, TileEntityCoalGeneratorAmazing te) {
		super(player, te, new ContainerCoalGeneratorAmazing(player, te), "coal/coal_generator");
	}
}
