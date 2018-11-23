package com.hqbanana.endgamestuffmod.blocks.generators.magma;

import com.hqbanana.endgamestuffmod.blocks.generators.BlockGeneratorBase;
import com.hqbanana.endgamestuffmod.tileentities.generators.magma.TileEntityMagmaGeneratorBase;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMagmaGeneratorBase extends BlockGeneratorBase {
	public BlockMagmaGeneratorBase(String name, Material material) {
		super(name, material);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityMagmaGeneratorBase te = (TileEntityMagmaGeneratorBase)worldIn.getTileEntity(pos);
		te.dropInventory(worldIn, pos);
		super.breakBlock(worldIn, pos, state);
	}
}
