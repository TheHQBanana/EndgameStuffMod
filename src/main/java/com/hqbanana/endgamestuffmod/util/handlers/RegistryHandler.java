package com.hqbanana.endgamestuffmod.util.handlers;

import com.hqbanana.endgamestuffmod.Main;
import com.hqbanana.endgamestuffmod.init.ModBlocks;
import com.hqbanana.endgamestuffmod.init.ModFluids;
import com.hqbanana.endgamestuffmod.init.ModItems;
import com.hqbanana.endgamestuffmod.init.ModRecipes;
import com.hqbanana.endgamestuffmod.world.ModWorldGen;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	
	public static void preInitRegistries() {
		ModFluids.registerFluids();
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
		RenderHandler.registerCustomMeshesAndStates();
		//registerEventHandlers();
	}
	
	public static void initRegistries() {
		ModRecipes.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
		Main.proxy.registerKeyBindings();
	}
	
	public static void postInitRegistries() {
		
	}
	
	public static void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(new FMLEventHandler());
	}
}
