package com.hqbanana.endgamestuffmod.guis.generators.magma;

import com.hqbanana.endgamestuffmod.containers.generators.magma.ContainerMagmaGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.tileentities.generators.magma.TileEntityMagmaGeneratorAdvanced;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiMagmaGeneratorAdvanced extends GuiMagmaGeneratorBase {
	protected TileEntityMagmaGeneratorAdvanced te;
	
	public GuiMagmaGeneratorAdvanced(InventoryPlayer player, TileEntityMagmaGeneratorAdvanced te) {
		super(player, te, new ContainerMagmaGeneratorAdvanced(player, te), "magma/magma_generator_advanced");
		this.te = te;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, 196, 166);
		drawBars();
	}
}
