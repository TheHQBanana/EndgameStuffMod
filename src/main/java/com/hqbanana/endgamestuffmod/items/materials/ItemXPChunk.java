package com.hqbanana.endgamestuffmod.items.materials;

import com.hqbanana.endgamestuffmod.items.ItemBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemXPChunk extends ItemBase {
	private int xpPerChunk = 5;
	
	public ItemXPChunk(String name) {
		super(name);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (!worldIn.isRemote) {
			playerIn.addExperience(playerIn.isSneaking() ? xpPerChunk * playerIn.getHeldItem(handIn).getCount() : xpPerChunk);
			playerIn.getHeldItem(handIn).shrink(playerIn.isSneaking() ? playerIn.getHeldItem(handIn).getCount() : 1);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}
