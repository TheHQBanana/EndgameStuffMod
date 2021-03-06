package com.hqbanana.endgamestuffmod.guis;

import com.hqbanana.endgamestuffmod.containers.ContainerRubyFurnace;
import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnace;
import com.hqbanana.endgamestuffmod.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import scala.Console;

public class GuiRubyFurnace extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/ruby_furnace.png");
	private final InventoryPlayer player;
	private final TileEntityRubyFurnace te;
	
	public GuiRubyFurnace(InventoryPlayer player, TileEntityRubyFurnace te) {
		super(new ContainerRubyFurnace(player,te));
		this.player = player;
		this.te = te;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.te.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2), 8, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if (TileEntityRubyFurnace.isBurning(te)) {
			int k = this.getBurnLeftScaled(13);
			this.drawTexturedModalRect(this.guiLeft + 57, this.guiTop + 37 + 12 - k, 176, 12 - k, 14, k + 1);
		}
		
		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 35, 176, 14, l + 1, 16);
	}
	
	private int getBurnLeftScaled(int pixels) {
		int i = this.te.getField(1);
		if (i == 0) i = 200;
		return this.te.getField(0) * pixels / i;
	}
	
	private int getCookProgressScaled(int pixels) {
		int i = this.te.getField(2);
		int j = this.te.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}
