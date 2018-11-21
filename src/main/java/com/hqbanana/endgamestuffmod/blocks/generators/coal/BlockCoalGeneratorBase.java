package com.hqbanana.endgamestuffmod.blocks.generators.coal;

import com.hqbanana.endgamestuffmod.blocks.generators.BlockGeneratorBase;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCoalGeneratorBase extends BlockGeneratorBase {
	public BlockCoalGeneratorBase(String name, Material material) {
		super(name, material);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityCoalGeneratorBase te = (TileEntityCoalGeneratorBase)worldIn.getTileEntity(pos);
		te.dropInventory(worldIn, pos);
		super.breakBlock(worldIn, pos, state);
	}
}
