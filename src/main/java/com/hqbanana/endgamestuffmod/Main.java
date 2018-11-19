package com.hqbanana.endgamestuffmod;

import com.hqbanana.endgamestuffmod.init.ModRecipes;
import com.hqbanana.endgamestuffmod.proxy.CommonProxy;
import com.hqbanana.endgamestuffmod.tabs.TabEndgameStuff;
import com.hqbanana.endgamestuffmod.util.Reference;
import com.hqbanana.endgamestuffmod.util.handlers.RegistryHandler;
import com.hqbanana.endgamestuffmod.world.ModWorldGen;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	public static final CreativeTabs ENDGAME_STUFF_TAB = new TabEndgameStuff("endgamestufftab");
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) { RegistryHandler.preInitRegistries(); }
	
	@EventHandler
	public static void init(FMLInitializationEvent event) { RegistryHandler.initRegistries(); }
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) { RegistryHandler.postInitRegistries(); }
}
