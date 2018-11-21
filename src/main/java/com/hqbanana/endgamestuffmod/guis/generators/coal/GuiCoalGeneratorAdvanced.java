package com.hqbanana.endgamestuffmod.guis.generators.coal;

import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorAdvanced;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCoalGeneratorAdvanced extends GuiCoalGeneratorBase {
	public GuiCoalGeneratorAdvanced(InventoryPlayer player, TileEntityCoalGeneratorAdvanced te) {
		super(player, te, new ContainerCoalGeneratorAdvanced(player, te), "coal/coal_generator_advanced");
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, 196, 166);
		drawBars();
	}
}
