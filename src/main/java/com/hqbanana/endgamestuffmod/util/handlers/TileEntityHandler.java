package com.hqbanana.endgamestuffmod.util.handlers;

import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnace;
import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorSimple;
import com.hqbanana.endgamestuffmod.tileentities.generators.magma.TileEntityMagmaGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.tileentities.generators.magma.TileEntityMagmaGeneratorSimple;
import com.hqbanana.endgamestuffmod.tileentities.machines.TileEntityDragonBreathFactory;
import com.hqbanana.endgamestuffmod.tileentities.machines.TileEntityLiquidXPConverter;
import com.hqbanana.endgamestuffmod.tileentities.machines.TileEntityWitherFactory;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityRubyFurnace.class, "ruby_furnace");
		GameRegistry.registerTileEntity(TileEntityRubyFurnaceElectric.class, "ruby_furnace_electric");
		//----------------Coal generators----------------//
		GameRegistry.registerTileEntity(TileEntityCoalGeneratorSimple.class, "coal_generator_simple");
		GameRegistry.registerTileEntity(TileEntityCoalGeneratorAdvanced.class, "coal_generator_advanced");
		//----------------Coal generators----------------//
		//----------------Magma generators----------------//
		GameRegistry.registerTileEntity(TileEntityMagmaGeneratorSimple.class, "magma_generator_simple");
		GameRegistry.registerTileEntity(TileEntityMagmaGeneratorAdvanced.class, "magma_generator_advanced");
		//----------------Magma generators----------------//
		//----------------Machines----------------//
		GameRegistry.registerTileEntity(TileEntityWitherFactory.class, "wither_factory");
		GameRegistry.registerTileEntity(TileEntityDragonBreathFactory.class, "dragon_breath_factory");
		GameRegistry.registerTileEntity(TileEntityLiquidXPConverter.class, "liquid_xp_converter");

		//----------------Machines----------------//
	}
}
