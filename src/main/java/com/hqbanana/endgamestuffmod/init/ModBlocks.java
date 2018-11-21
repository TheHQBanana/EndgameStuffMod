package com.hqbanana.endgamestuffmod.init;

import java.util.ArrayList;
import java.util.List;
import com.hqbanana.endgamestuffmod.blocks.BlockRuby;
import com.hqbanana.endgamestuffmod.blocks.BlockRubyFurnace;
import com.hqbanana.endgamestuffmod.blocks.BlockRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.blocks.BlockRubyOre;
import com.hqbanana.endgamestuffmod.blocks.generators.coal.BlockCoalGeneratorAmazing;
import com.hqbanana.endgamestuffmod.blocks.generators.coal.BlockCoalGeneratorDecent;
import com.hqbanana.endgamestuffmod.blocks.generators.coal.BlockCoalGeneratorEfficient;
import com.hqbanana.endgamestuffmod.blocks.generators.coal.BlockCoalGeneratorOkay;
import com.hqbanana.endgamestuffmod.blocks.generators.coal.BlockCoalGeneratorOverkill;
import com.hqbanana.endgamestuffmod.blocks.generators.coal.BlockCoalGeneratorTerrible;

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
	public static final Block COAL_GENERATOR_TERRIBLE = new BlockCoalGeneratorTerrible("coal_generator_terrible", Material.IRON);
	public static final Block COAL_GENERATOR_OKAY = new BlockCoalGeneratorOkay("coal_generator_okay", Material.IRON);
	public static final Block COAL_GENERATOR_DECENT = new BlockCoalGeneratorDecent("coal_generator_decent", Material.IRON);
	public static final Block COAL_GENERATOR_EFFICIENT = new BlockCoalGeneratorEfficient("coal_generator_efficient", Material.IRON);
	public static final Block COAL_GENERATOR_AMAZING = new BlockCoalGeneratorAmazing("coal_generator_amazing", Material.IRON);
	public static final Block COAL_GENERATOR_OVERKILL = new BlockCoalGeneratorOverkill("coal_generator_overkill", Material.IRON);
}
