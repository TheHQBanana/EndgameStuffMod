package com.hqbanana.endgamestuffmod.guis.generators.coal;

import java.util.ArrayList;
import java.util.List;

import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorTerrible;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorTerrible;
import com.hqbanana.endgamestuffmod.util.GuiHelper;
import com.hqbanana.endgamestuffmod.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import scala.Console;

public class GuiCoalGeneratorOkay extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/generators/coal/coal_generator_terrible.png");
	private final InventoryPlayer player;
	private final TileEntityCoalGeneratorTerrible te;
	
	public GuiCoalGeneratorOkay(InventoryPlayer player, TileEntityCoalGeneratorTerrible te) {
		super(new ContainerCoalGeneratorTerrible(player,te));
		this.player = player;
		this.te = te;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.te.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2), 4, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);
		if (GuiHelper.isMouseInRect(this.guiLeft + 151, this.guiTop + 17, 16, 57, mouseX, mouseY)) {
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
		
		int l = this.getBurnProgressScaled(13);
		this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 32 + 12 - l, 176, 13 - l, 14, l + 1);
		
		int k = this.getEnergyStoredScaled(57);
		this.drawTexturedModalRect(this.guiLeft + 151, this.guiTop + 17 + 57 - k, 176, 71 - k, 16, k + 1);
	}
	
	private int getEnergyStoredScaled(int pixels) {
		int i = this.te.getEnergyStored();
		int j = this.te.getMaxEnergyStored();
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
	
	private int getBurnProgressScaled(int pixels) {
		int i = this.te.getCurrentBurnTime();
		int j = this.te.getTotalBurnTime();
		
		Console.out().println("I: " + i + ", J: " + j);
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
}
