package com.hqbanana.endgamestuffmod.util.handlers;

import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnace;
import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorSimple;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityRubyFurnace.class, "ruby_furnace");
		GameRegistry.registerTileEntity(TileEntityRubyFurnaceElectric.class, "ruby_furnace_electric");
		//----------------Coal generators----------------//
		GameRegistry.registerTileEntity(TileEntityCoalGeneratorSimple.class, "coal_generator_terrible");
		GameRegistry.registerTileEntity(TileEntityCoalGeneratorAdvanced.class, "coal_generator_okay");
		//----------------Coal generators----------------//
	}
}
