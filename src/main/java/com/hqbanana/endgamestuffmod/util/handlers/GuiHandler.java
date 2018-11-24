package com.hqbanana.endgamestuffmod.util.handlers;

import com.hqbanana.endgamestuffmod.containers.ContainerRubyFurnace;
import com.hqbanana.endgamestuffmod.containers.ContainerRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.containers.generators.coal.ContainerCoalGeneratorSimple;
import com.hqbanana.endgamestuffmod.containers.generators.magma.ContainerMagmaGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.containers.generators.magma.ContainerMagmaGeneratorSimple;
import com.hqbanana.endgamestuffmod.containers.machines.ContainerDragonBreathFactory;
import com.hqbanana.endgamestuffmod.containers.machines.ContainerWitherFactory;
import com.hqbanana.endgamestuffmod.guis.GuiRubyFurnace;
import com.hqbanana.endgamestuffmod.guis.GuiRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.guis.generators.coal.GuiCoalGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.guis.generators.coal.GuiCoalGeneratorSimple;
import com.hqbanana.endgamestuffmod.guis.generators.magma.GuiMagmaGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.guis.generators.magma.GuiMagmaGeneratorSimple;
import com.hqbanana.endgamestuffmod.guis.machines.GuiDragonBreathFactory;
import com.hqbanana.endgamestuffmod.guis.machines.GuiWitherFactory;
import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnace;
import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorSimple;
import com.hqbanana.endgamestuffmod.tileentities.generators.magma.TileEntityMagmaGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.tileentities.generators.magma.TileEntityMagmaGeneratorSimple;
import com.hqbanana.endgamestuffmod.tileentities.machines.TileEntityDragonBreathFactory;
import com.hqbanana.endgamestuffmod.tileentities.machines.TileEntityWitherFactory;
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
		//---------------Magma generators---------------//
		if (ID == Reference.GUI_MAGMA_GENERATOR_SIMPLE) return new ContainerMagmaGeneratorSimple(player.inventory, (TileEntityMagmaGeneratorSimple)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_MAGMA_GENERATOR_ADVANCED) return new ContainerMagmaGeneratorAdvanced(player.inventory, (TileEntityMagmaGeneratorAdvanced)world.getTileEntity(new BlockPos(x, y, z)));
		//---------------Magma generators---------------//
		//---------------Machines---------------//
		if (ID == Reference.GUI_WITHER_FACTORY) return new ContainerWitherFactory(player.inventory, (TileEntityWitherFactory)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_DRAGON_BREATH_FACTORY) return new ContainerDragonBreathFactory(player.inventory, (TileEntityDragonBreathFactory)world.getTileEntity(new BlockPos(x, y, z)));
		//---------------Machines---------------//
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
		//---------------Magma generators---------------//
		if (ID == Reference.GUI_MAGMA_GENERATOR_SIMPLE) return new GuiMagmaGeneratorSimple(player.inventory, (TileEntityMagmaGeneratorSimple)world.getTileEntity(new BlockPos(x, y, z)));
		if (ID == Reference.GUI_MAGMA_GENERATOR_ADVANCED) return new GuiMagmaGeneratorAdvanced(player.inventory, (TileEntityMagmaGeneratorAdvanced)world.getTileEntity(new BlockPos(x, y, z)));
		//---------------Magma generators---------------//
		//---------------Machines---------------//
		if (ID == Reference.GUI_WITHER_FACTORY) return new GuiWitherFactory(player.inventory, (TileEntityWitherFactory)world.getTileEntity(new BlockPos(x, y, z)), new ContainerWitherFactory(player.inventory, (TileEntityWitherFactory)world.getTileEntity(new BlockPos(x, y, z))), "wither_factory");
		if (ID == Reference.GUI_DRAGON_BREATH_FACTORY) return new GuiDragonBreathFactory(player.inventory, (TileEntityDragonBreathFactory)world.getTileEntity(new BlockPos(x, y, z)), new ContainerDragonBreathFactory(player.inventory, (TileEntityDragonBreathFactory)world.getTileEntity(new BlockPos(x, y, z))), "dragon_breath_factory");
		//---------------Machines---------------//
		return null;
	}
	
}
