package com.hqbanana.endgamestuffmod.blocks;

import java.util.Random;

import com.hqbanana.endgamestuffmod.Main;
import com.hqbanana.endgamestuffmod.init.ModItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockRubyOre extends BlockBase {
	public BlockRubyOre(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.STONE);
		setHardness(2.0f);
		setResistance(10.0f);
		setHarvestLevel("pickaxe", 1);
		setCreativeTab(Main.ENDGAME_STUFF_TAB);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.RUBY;
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random rand) {
		int minDropped = (3 * fortune) + 3;
		int maxDropped = (7 * fortune) + 7;
		return rand.nextInt(maxDropped) + minDropped;
	}
}
