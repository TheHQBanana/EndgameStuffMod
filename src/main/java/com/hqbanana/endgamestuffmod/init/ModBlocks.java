package com.hqbanana.endgamestuffmod.init;

import java.util.ArrayList;
import java.util.List;
import com.hqbanana.endgamestuffmod.blocks.BlockRuby;
import com.hqbanana.endgamestuffmod.blocks.BlockRubyFurnace;
import com.hqbanana.endgamestuffmod.blocks.BlockRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.blocks.BlockRubyOre;
import com.hqbanana.endgamestuffmod.blocks.generators.coal.BlockCoalGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.blocks.generators.coal.BlockCoalGeneratorSimple;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Blocks
	public static final Block RUBY_BLOCK = new BlockRuby("ruby_block", Material.IRON);
	public static final Block RUBY_ORE = new BlockRubyOre("ruby_ore", Material.ROCK);
	public static final Block RUBY_FURNACE = new BlockRubyFurnace("ruby_furnace", Material.ROCK);
	public static final Block RUBY_FURNACE_ELECTRIC = new BlockRubyFurnaceElectric("ruby_furnace_electric", Material.ROCK);
	
	//Coal generators
	public static final Block COAL_GENERATOR_TERRIBLE = new BlockCoalGeneratorSimple("coal_generator_simple", Material.IRON);
	public static final Block COAL_GENERATOR_OKAY = new BlockCoalGeneratorAdvanced("coal_generator_advanced", Material.IRON);
	}
