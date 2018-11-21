package com.hqbanana.endgamestuffmod.guis.generators;

import java.util.ArrayList;
import java.util.List;

import com.hqbanana.endgamestuffmod.containers.generators.ContainerGeneratorBase;
import com.hqbanana.endgamestuffmod.tileentities.generators.TileEntityGeneratorBase;
import com.hqbanana.endgamestuffmod.util.GuiHelper;
import com.hqbanana.endgamestuffmod.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GuiGeneratorBase extends GuiContainer {
	protected ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/generators/coal/coal_generator_simple.png");
	protected final InventoryPlayer player;
	protected final TileEntityGeneratorBase te;
	
	public GuiGeneratorBase(InventoryPlayer player, TileEntityGeneratorBase te, ContainerGeneratorBase cgb, String guiPath) {
		super(cgb);
		this.player = player;
		this.te = te;
		this.TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/generators/" + guiPath + ".png");
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		drawBars();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.te.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2), 8, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);
		drawPowerHover(mouseX, mouseY);
	}
	
	protected int getBurnProgressScaled(int pixels) {
		int i = this.te.getCurrentBurnTime();
		int j = this.te.getTotalBurnTime();
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
	
	protected int getEnergyStoredScaled(int pixels) {
		int i = this.te.getEnergyStored();
		int j = this.te.getMaxEnergyStored();
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
	
	protected void drawBars() {
		int l = this.getBurnProgressScaled(13);
		this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 32 + 12 - l, 196, 13 - l, 14, l + 1);
		
		int k = this.getEnergyStoredScaled(57);
		this.drawTexturedModalRect(this.guiLeft + 151, this.guiTop + 17 + 57 - k, 196, 71 - k, 16, k + 1);
	}
	
	protected void drawPowerHover(int mouseX, int mouseY) {
		if (GuiHelper.isMouseInRect(this.guiLeft + 151, this.guiTop + 17, 16, 57, mouseX, mouseY)) {
			int k = (this.width - this.xSize) / 2;
			int l = (this.height - this.ySize) / 2;
			List<String> list = new ArrayList<String>();
			list.add(TextFormatting.RED + "Power: ");
			list.add(TextFormatting.RED + (this.te.getEnergyStored() + " FE / " + this.te.getMaxEnergyStored() + " FE"));
			this.drawHoveringText(list, mouseX - k, mouseY - l, this.fontRenderer);
		}
	}
}
