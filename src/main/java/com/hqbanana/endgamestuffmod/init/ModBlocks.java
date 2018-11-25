package com.hqbanana.endgamestuffmod.init;

import java.util.ArrayList;
import java.util.List;

import com.hqbanana.endgamestuffmod.blocks.BlockFluid;
import com.hqbanana.endgamestuffmod.blocks.BlockRuby;
import com.hqbanana.endgamestuffmod.blocks.BlockRubyFurnace;
import com.hqbanana.endgamestuffmod.blocks.BlockRubyFurnaceElectric;
import com.hqbanana.endgamestuffmod.blocks.BlockRubyOre;
import com.hqbanana.endgamestuffmod.blocks.generators.coal.BlockCoalGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.blocks.generators.coal.BlockCoalGeneratorSimple;
import com.hqbanana.endgamestuffmod.blocks.generators.magma.BlockMagmaGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.blocks.generators.magma.BlockMagmaGeneratorSimple;
import com.hqbanana.endgamestuffmod.blocks.machines.BlockDragonBreathFactory;
import com.hqbanana.endgamestuffmod.blocks.machines.BlockWitherFactory;
import com.hqbanana.endgamestuffmod.blocks.materials.BlockNetherStar;
import com.hqbanana.endgamestuffmod.blocks.materials.BlockNethererStar;
import com.hqbanana.endgamestuffmod.blocks.materials.BlockNetherestStar;

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
	public static final Block COAL_GENERATOR_SIMPLE = new BlockCoalGeneratorSimple("coal_generator_simple", Material.IRON);
	public static final Block COAL_GENERATOR_ADVANCED = new BlockCoalGeneratorAdvanced("coal_generator_advanced", Material.IRON);
	
	//Magma generator
	public static final Block MAGMA_GENERATOR_SIMPLE = new BlockMagmaGeneratorSimple("magma_generator_simple", Material.IRON);
	public static final Block MAGMA_GENERATOR_ADVANCED = new BlockMagmaGeneratorAdvanced("magma_generator_advanced", Material.IRON);
	
	//Materials
	public static final Block NETHER_STAR_BLOCK = new BlockNetherStar("nether_star_block", Material.ROCK);
	public static final Block NETHERER_STAR_BLOCK = new BlockNethererStar("netherer_star_block", Material.ROCK);
	public static final Block NETHEREST_STAR_BLOCK = new BlockNetherestStar("netherest_star_block", Material.ROCK);
	
	//Machines
	public static final Block WITHER_FACTORY = new BlockWitherFactory("wither_factory", Material.IRON);
	public static final Block DRAGON_BREATH_FACTORY = new BlockDragonBreathFactory("dragon_breath_factory", Material.IRON);
	
	//Liquids
	public static final Block LIQUID_XP_BLOCK = new BlockFluid("liquid_xp", ModFluids.LIQUID_XP, Material.LAVA);
}
