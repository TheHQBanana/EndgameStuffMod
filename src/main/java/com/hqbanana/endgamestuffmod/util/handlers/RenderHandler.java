package com.hqbanana.endgamestuffmod.util.handlers;

import com.hqbanana.endgamestuffmod.init.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class RenderHandler {
	public static void registerCustomMeshesAndStates() {
		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.LIQUID_XP_BLOCK), new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return new ModelResourceLocation("egsm:liquid_xp", "fluid");
			}
		});
		
		ModelLoader.setCustomStateMapper(ModBlocks.LIQUID_XP_BLOCK, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("egsm:liquid_xp", "fluid");
			}
		});
	}
}