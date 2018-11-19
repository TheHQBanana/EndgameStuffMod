package com.hqbanana.endgamestuffmod.init;

import java.util.ArrayList;
import java.util.List;
import com.hqbanana.endgamestuffmod.blocks.BlockBase;
import com.hqbanana.endgamestuffmod.blocks.BlockRuby;
import com.hqbanana.endgamestuffmod.blocks.BlockRubyFurnace;
import com.hqbanana.endgamestuffmod.blocks.BlockRubyOre;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Blocks
	public static final Block RUBY_BLOCK = new BlockRuby("ruby_block", Material.IRON);
	public static final Block RUBY_ORE = new BlockRubyOre("ruby_ore", Material.ROCK);
	public static final Block RUBY_FURNACE = new BlockRubyFurnace("ruby_furnace", Material.ROCK);
}