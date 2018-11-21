package com.hqbanana.endgamestuffmod.util.handlers;

import com.hqbanana.endgamestuffmod.Main;
import com.hqbanana.endgamestuffmod.init.ModBlocks;
import com.hqbanana.endgamestuffmod.init.ModItems;
import com.hqbanana.endgamestuffmod.util.EnumUpgrade;
import com.hqbanana.endgamestuffmod.util.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value=Side.CLIENT, modid=Reference.MOD_ID)
public class ModelHandler {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
    	System.out.println("MODEL REGISTRY BEING EXECUTED!!");
    	//----------------Reference items----------------//
        ModelLoader.setCustomModelResourceLocation(ModItems.RUBY, 0,
                new ModelResourceLocation(ModItems.RUBY.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.RUBY_BLOCK), 0,
                new ModelResourceLocation(Item.getItemFromBlock(ModBlocks.RUBY_BLOCK).getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.RUBY_FURNACE), 0,
                new ModelResourceLocation(Item.getItemFromBlock(ModBlocks.RUBY_FURNACE).getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.RUBY_FURNACE_ELECTRIC), 0,
                new ModelResourceLocation(Item.getItemFromBlock(ModBlocks.RUBY_FURNACE_ELECTRIC).getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.RUBY_ORE), 0,
                new ModelResourceLocation(Item.getItemFromBlock(ModBlocks.RUBY_ORE).getRegistryName(), "inventory"));
      //----------------Reference items----------------//
      //----------------Coal generators----------------//
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.COAL_GENERATOR_TERRIBLE), 0,
                new ModelResourceLocation(Item.getItemFromBlock(ModBlocks.COAL_GENERATOR_TERRIBLE).getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.COAL_GENERATOR_OKAY), 0,
                new ModelResourceLocation(Item.getItemFromBlock(ModBlocks.COAL_GENERATOR_OKAY).getRegistryName(), "inventory"));
      //----------------Coal generators----------------//
      //----------------Upgrades----------------//
        NonNullList<ItemStack> list = NonNullList.create();
        ModItems.GENERATOR_UPGRADE_SPEED.getSubItems(Main.ENDGAME_STUFF_TAB, list);
        for (ItemStack itemStack : list) {
        	System.out.println("Name: " + "_" + EnumUpgrade.byUpgradeDamage(itemStack.getMetadata()).getTranslationKey());
        	ModelLoader.setCustomModelResourceLocation(ModItems.GENERATOR_UPGRADE_SPEED, itemStack.getItemDamage(),
        			new ModelResourceLocation(ModItems.GENERATOR_UPGRADE_SPEED.getRegistryName() + "_" + EnumUpgrade.byUpgradeDamage(itemStack.getMetadata()).getTranslationKey(), "inventory"));
        }
        //----------------Upgrades----------------//
    }
}
