package com.hqbanana.endgamestuffmod.util.handlers;

import com.hqbanana.endgamestuffmod.containers.ContainerRubyFurnace;
import com.hqbanana.endgamestuffmod.containers.ContainerRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorAmazing;
import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorDecent;
import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorEfficient;
import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorOkay;
import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorOverkill;
import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorTerrible;
import com.hqbanana.endgamestuffmod.guis.GuiRubyFurnace;
import com.hqbanana.endgamestuffmod.guis.GuiRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.guis.generators.coal.GuiCoalGeneratorAmazing;
import com.hqbanana.endgamestuffmod.guis.generators.coal.GuiCoalGeneratorDecent;
import com.hqbanana.endgamestuffmod.guis.generators.coal.GuiCoalGeneratorEfficient;
import com.hqbanana.endgamestuffmod.guis.generators.coal.GuiCoalGeneratorOkay;
import com.hqbanana.endgamestuffmod.guis.generators.coal.GuiCoalGeneratorOverkill;
import com.hqbanana.endgamestuffmod.guis.generators.coal.GuiCoalGeneratorTerrible;
import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnace;
import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorAmazing;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorDecent;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorEfficient;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorOkay;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorOverkill;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorTerrible;
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
		if (ID == Reference.GUI_COAL_GENERATOR_TERRIBLE) return new ContainerCoalGeneratorTerrible(player.inventory, (TileEntityCoalGeneratorTerrible)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_COAL_GENERATOR_OKAY) return new ContainerCoalGeneratorOkay(player.inventory, (TileEntityCoalGeneratorOkay)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_COAL_GENERATOR_DECENT) return new ContainerCoalGeneratorDecent(player.inventory, (TileEntityCoalGeneratorDecent)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_COAL_GENERATOR_EFFICIENT) return new ContainerCoalGeneratorEfficient(player.inventory, (TileEntityCoalGeneratorEfficient)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_COAL_GENERATOR_AMAZING) return new ContainerCoalGeneratorAmazing(player.inventory, (TileEntityCoalGeneratorAmazing)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_COAL_GENERATOR_OVERKILL) return new ContainerCoalGeneratorOverkill(player.inventory, (TileEntityCoalGeneratorOverkill)world.getTileEntity(new BlockPos(x, y, z)));
		//---------------Coal generators---------------//
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_RUBY_FURNACE) return new GuiRubyFurnace(player.inventory, (TileEntityRubyFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_RUBY_FURNACE_ELECTRIC) return new GuiRubyFurnaceElectric(player.inventory, (TileEntityRubyFurnaceElectric)world.getTileEntity(new BlockPos(x, y, z)));
		//---------------Coal generators---------------//
		if (ID == Reference.GUI_COAL_GENERATOR_TERRIBLE) return new GuiCoalGeneratorTerrible(player.inventory, (TileEntityCoalGeneratorTerrible)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_COAL_GENERATOR_OKAY) return new GuiCoalGeneratorOkay(player.inventory, (TileEntityCoalGeneratorOkay)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_COAL_GENERATOR_DECENT) return new GuiCoalGeneratorDecent(player.inventory, (TileEntityCoalGeneratorDecent)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_COAL_GENERATOR_EFFICIENT) return new GuiCoalGeneratorEfficient(player.inventory, (TileEntityCoalGeneratorEfficient)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_COAL_GENERATOR_AMAZING) return new GuiCoalGeneratorAmazing(player.inventory, (TileEntityCoalGeneratorAmazing)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_COAL_GENERATOR_OVERKILL) return new GuiCoalGeneratorOverkill(player.inventory, (TileEntityCoalGeneratorOverkill)world.getTileEntity(new BlockPos(x, y, z)));
		//---------------Coal generators---------------//
		return null;
	}
	
}
