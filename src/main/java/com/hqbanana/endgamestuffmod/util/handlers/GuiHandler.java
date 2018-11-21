package com.hqbanana.endgamestuffmod.util.handlers;

import com.hqbanana.endgamestuffmod.containers.ContainerRubyFurnace;
import com.hqbanana.endgamestuffmod.containers.ContainerRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorSimple;
import com.hqbanana.endgamestuffmod.guis.GuiRubyFurnace;
import com.hqbanana.endgamestuffmod.guis.GuiRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.guis.generators.coal.GuiCoalGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.guis.generators.coal.GuiCoalGeneratorSimple;
import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnace;
import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorSimple;
import com.hqbanana.endgamestuffmod.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_RUBY_FURNACE) return new ContainerRubyFurnace(player.inventory, (TileEntityRubyFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_RUBY_FURNACE_ELECTRIC) return new ContainerRubyFurnaceElectric(player.inventory, (TileEntityRubyFurnaceElectric)world.getTileEntity(new BlockPos(x, y, z)));
		//---------------Coal generators---------------//
		if (ID == Reference.GUI_COAL_GENERATOR_SIMPLE) return new ContainerCoalGeneratorSimple(player.inventory, (TileEntityCoalGeneratorSimple)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_COAL_GENERATOR_ADVANCED) return new ContainerCoalGeneratorAdvanced(player.inventory, (TileEntityCoalGeneratorAdvanced)world.getTileEntity(new BlockPos(x, y, z)));
		//---------------Coal generators---------------//
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_RUBY_FURNACE) return new GuiRubyFurnace(player.inventory, (TileEntityRubyFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_RUBY_FURNACE_ELECTRIC) return new GuiRubyFurnaceElectric(player.inventory, (TileEntityRubyFurnaceElectric)world.getTileEntity(new BlockPos(x, y, z)));
		//---------------Coal generators---------------//
		if (ID == Reference.GUI_COAL_GENERATOR_SIMPLE) return new GuiCoalGeneratorSimple(player.inventory, (TileEntityCoalGeneratorSimple)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_COAL_GENERATOR_ADVANCED) return new GuiCoalGeneratorAdvanced(player.inventory, (TileEntityCoalGeneratorAdvanced)world.getTileEntity(new BlockPos(x, y, z)));
		//---------------Coal generators---------------//
		return null;
	}
	
}
