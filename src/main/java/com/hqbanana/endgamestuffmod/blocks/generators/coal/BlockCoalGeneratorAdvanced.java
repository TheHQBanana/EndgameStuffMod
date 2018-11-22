package com.hqbanana.endgamestuffmod.blocks.generators.coal;

import java.util.Random;

import com.hqbanana.endgamestuffmod.Main;
import com.hqbanana.endgamestuffmod.init.ModBlocks;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.util.Reference;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCoalGeneratorAdvanced extends BlockCoalGeneratorBase {
	public BlockCoalGeneratorAdvanced(String name, Material material) {
		super(name, material);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.COAL_GENERATOR_ADVANCED);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(ModBlocks.COAL_GENERATOR_ADVANCED);
	}
	
	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity te = worldIn.getTileEntity(pos);
		worldIn.setBlockState(pos,  ModBlocks.COAL_GENERATOR_ADVANCED.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BURNING, active), 3);
		
		if(te != null) {
			te.validate();
			worldIn.setTileEntity(pos, te);
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCoalGeneratorAdvanced();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			playerIn.openGui(Main.instance, Reference.GUI_COAL_GENERATOR_ADVANCED, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
}
