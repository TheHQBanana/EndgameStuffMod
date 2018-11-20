package com.hqbanana.endgamestuffmod.guis;

import java.util.ArrayList;
import java.util.List;

import com.hqbanana.endgamestuffmod.containers.ContainerRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.util.GuiHelper;
import com.hqbanana.endgamestuffmod.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GuiRubyFurnaceElectric extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/ruby_furnace_electric.png");
	private final InventoryPlayer player;
	private final TileEntityRubyFurnaceElectric te;
	
	public GuiRubyFurnaceElectric(InventoryPlayer player, TileEntityRubyFurnaceElectric te) {
		super(new ContainerRubyFurnaceElectric(player,te));
		this.player = player;
		this.te = te;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.te.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2), 4, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);
		if (GuiHelper.isMouseInRect(this.guiLeft + 8, this.guiTop + 17, 16, 57, mouseX, mouseY)) {
			int k = (this.width - this.xSize) / 2;
			int l = (this.height - this.ySize) / 2;
			List<String> list = new ArrayList<String>();
			list.add(TextFormatting.RED + "Power: ");
			list.add(TextFormatting.RED + (this.te.getEnergyStored() + " FE / " + this.te.getMaxEnergyStored() + " FE"));
			this.drawHoveringText(list, mouseX - k, mouseY - l, this.fontRenderer);
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 35, 176, 14, l + 1, 16);
		
		int k = this.getEnergyStoredScaled(57);
		this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 17 + 57 - k, 176, 88 - k, 16, k + 1);
	}
	
	private int getEnergyStoredScaled(int pixels) {
		int i = this.te.getEnergyStored();
		int j = this.te.getMaxEnergyStored();
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
	
	private int getCookProgressScaled(int pixels) {
		int i = this.te.getField(0);
		return i != 0 ? i * pixels / 100 : 0;
	}
}
