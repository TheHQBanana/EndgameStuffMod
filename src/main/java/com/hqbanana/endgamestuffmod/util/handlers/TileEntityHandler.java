package com.hqbanana.endgamestuffmod.util.handlers;

import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnace;
import com.hqbanana.endgamestuffmod.tileentities.TileEntityRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorAmazing;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorDecent;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorEfficient;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorOkay;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorOverkill;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorTerrible;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityRubyFurnace.class, "ruby_furnace");
		GameRegistry.registerTileEntity(TileEntityRubyFurnaceElectric.class, "ruby_furnace_electric");
		//----------------Coal generators----------------//
		GameRegistry.registerTileEntity(TileEntityCoalGeneratorTerrible.class, "coal_generator_terrible");
		GameRegistry.registerTileEntity(TileEntityCoalGeneratorOkay.class, "coal_generator_okay");
		GameRegistry.registerTileEntity(TileEntityCoalGeneratorDecent.class, "coal_generator_decent");
		GameRegistry.registerTileEntity(TileEntityCoalGeneratorEfficient.class, "coal_generator_efficient");
		GameRegistry.registerTileEntity(TileEntityCoalGeneratorAmazing.class, "coal_generator_amazing");
		GameRegistry.registerTileEntity(TileEntityCoalGeneratorOverkill.class, "coal_generator_overkill");
		//----------------Coal generators----------------//
	}
}
