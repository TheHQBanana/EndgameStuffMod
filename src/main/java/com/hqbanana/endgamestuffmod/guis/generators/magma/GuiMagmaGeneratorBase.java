package com.hqbanana.endgamestuffmod.guis.generators.magma;

import java.util.ArrayList;
import java.util.List;

import com.hqbanana.endgamestuffmod.containers.generators.ContainerGeneratorBase;
import com.hqbanana.endgamestuffmod.guis.generators.GuiGeneratorBase;
import com.hqbanana.endgamestuffmod.tileentities.generators.magma.TileEntityMagmaGeneratorBase;
import com.hqbanana.endgamestuffmod.util.GuiHelper;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.text.TextFormatting;

public class GuiMagmaGeneratorBase extends GuiGeneratorBase {
	protected TileEntityMagmaGeneratorBase te;
	
	public GuiMagmaGeneratorBase(InventoryPlayer player, TileEntityMagmaGeneratorBase te, ContainerGeneratorBase cgb, String guiPath) {
		super(player, te, cgb, guiPath);
		this.te = te;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, 176, 166);
		drawBars();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.te.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2), 8, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);
		drawPowerHover(mouseX, mouseY);
		drawMagmaHover(mouseX, mouseY);
	}
	
	protected void drawMagmaHover(int mouseX, int mouseY) {
		if (GuiHelper.isMouseInRect(this.guiLeft + 10, this.guiTop + 15, 16, 59, mouseX, mouseY)) {
			int k = (this.width - this.xSize) / 2;
			int l = (this.height - this.ySize) / 2;
			List<String> list = new ArrayList<String>();
			list.add(TextFormatting.RED + "Magma: ");
			list.add(TextFormatting.RED + (this.te.getFluidAmountStored() + " mB / " + this.te.getMaxFluidAmountStored() + " mB"));
			this.drawHoveringText(list, mouseX - k, mouseY - l, this.fontRenderer);
		}
	}
	
	protected int getFluidStoredScaled(int pixels) {
		int i = this.te.getFluidAmountStored();
		int j = this.te.getMaxFluidAmountStored();
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
	
	@Override
	protected void drawBars() {
		int l = this.getBurnProgressScaled(13);
		this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 32 + 12 - l, 196, 13 - l, 14, l + 1);
		
		int k = this.getEnergyStoredScaled(59);
		this.drawTexturedModalRect(this.guiLeft + 151, this.guiTop + 15 + 59 - k, 196, 73 - k, 16, k + 1);
		
		int j = this.getFluidStoredScaled(59);
		this.drawTexturedModalRect(this.guiLeft + 10, this.guiTop + 15 + 59 - j, 228, 59 - j, 16, j + 1);
	}
}
