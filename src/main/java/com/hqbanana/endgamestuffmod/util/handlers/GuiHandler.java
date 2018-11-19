package com.hqbanana.endgamestuffmod.util.handlers;

import com.hqbanana.endgamestuffmod.containers.ContainerRubyFurnace;
import com.hqbanana.endgamestuffmod.guis.GuiRubyFurnace;
import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnace;
import com.hqbanana.endgamestuffmod.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_RUBY_FURNACE) return new ContainerRubyFurnace(player.inventory, (TileEntityRubyFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_RUBY_FURNACE) return new GuiRubyFurnace(player.inventory, (TileEntityRubyFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}
	
}
