package com.hqbanana.endgamestuffmod.guis.machines;

import java.util.ArrayList;
import java.util.List;

import com.hqbanana.endgamestuffmod.containers.machines.ContainerDragonBreathFactory;
import com.hqbanana.endgamestuffmod.containers.machines.ContainerWitherFactory;
import com.hqbanana.endgamestuffmod.tileentities.machines.TileEntityDragonBreathFactory;
import com.hqbanana.endgamestuffmod.tileentities.machines.TileEntityWitherFactory;
import com.hqbanana.endgamestuffmod.util.GuiHelper;
import com.hqbanana.endgamestuffmod.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GuiDragonBreathFactory extends GuiContainer {
	protected ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/machines/dragon_breath_factory.png");
	protected final InventoryPlayer player;
	protected final TileEntityDragonBreathFactory te;
	
	public GuiDragonBreathFactory(InventoryPlayer player, TileEntityDragonBreathFactory te, ContainerDragonBreathFactory cdbf, String guiPath) {
		super(cdbf);
		this.player = player;
		this.te = te;
		this.TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/machines/" + guiPath + ".png");
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, 196, this.ySize);
		drawBars();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.te.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2), 8, 4210752);
		drawPowerHover(mouseX, mouseY);
		drawLiquidHover(mouseX, mouseY);
	}
	
	protected int getBreathProgressScaled(int pixels) {
		int i = this.te.getCurrentProgressTime();
		int j = this.te.getTotalProgressTime();
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
	
	protected int getEnergyStoredScaled(int pixels) {
		int i = this.te.getEnergyStored();
		int j = this.te.getMaxEnergyStored();
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
	
	protected int getLiquidStoredScaled(int pixels) {
		int i = this.te.getFluidAmountStored();
		int j = this.te.getMaxFluidAmountStored();
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
	
	protected void drawBars() {
		int l = this.getBreathProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 99, this.guiTop + 39, 196, 59, l + 1, 17);
		
		int k = this.getEnergyStoredScaled(59);
		this.drawTexturedModalRect(this.guiLeft + 10, this.guiTop + 15 + 59 - k, 196, 59 - k, 16, k + 1);
		
		int j = this.getLiquidStoredScaled(59);
		this.drawTexturedModalRect(this.guiLeft + 151, this.guiTop + 15 + 59 - j, 212, 59 - j, 16, j + 1);
	}
	
	protected void drawPowerHover(int mouseX, int mouseY) {
		if (GuiHelper.isMouseInRect(this.guiLeft + 10, this.guiTop + 15, 16, 59, mouseX, mouseY)) {
			int k = (this.width - this.xSize) / 2;
			int l = (this.height - this.ySize) / 2;
			List<String> list = new ArrayList<String>();
			list.add(TextFormatting.RED + "Power: ");
			list.add(TextFormatting.RED + (this.te.getEnergyStored() + " FE / " + this.te.getMaxEnergyStored() + " FE"));
			this.drawHoveringText(list, mouseX - k, mouseY - l, this.fontRenderer);
		}
	}
	
	protected void drawLiquidHover(int mouseX, int mouseY) {
		if (GuiHelper.isMouseInRect(this.guiLeft + 151, this.guiTop + 14, 16, 59, mouseX, mouseY)) {
			int k = (this.width - this.xSize) / 2;
			int l = (this.height - this.ySize) / 2;
			List<String> list = new ArrayList<String>();
			list.add(TextFormatting.RED + "Liquid XP: ");
			list.add(TextFormatting.RED + (this.te.getFluidAmountStored() + " mB / " + this.te.getMaxFluidAmountStored() + " mB"));
			this.drawHoveringText(list, mouseX - k, mouseY - l, this.fontRenderer);
		}
	}
}
