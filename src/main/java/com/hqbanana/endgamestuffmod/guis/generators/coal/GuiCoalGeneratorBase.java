package com.hqbanana.endgamestuffmod.guis.generators.coal;

import com.hqbanana.endgamestuffmod.containers.generators.ContainerGeneratorBase;
import com.hqbanana.endgamestuffmod.guis.generators.GuiGeneratorBase;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorBase;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCoalGeneratorBase extends GuiGeneratorBase {
	public GuiCoalGeneratorBase(InventoryPlayer player, TileEntityCoalGeneratorBase te, ContainerGeneratorBase cgb, String guiPath) {
		super(player, te, cgb, guiPath);
	}
}
