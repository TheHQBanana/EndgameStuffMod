package com.hqbanana.endgamestuffmod.proxy;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = "egsm", value = Side.CLIENT)
public class ClientProxy extends CommonProxy {
	public static KeyBinding[] keyBindings;
	
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	public void registerKeyBindings() {
		keyBindings = new KeyBinding[2];
		
		keyBindings[0] = new KeyBinding("key.xpshardl.desc", Keyboard.KEY_LSHIFT, "key.endgamestuff.category");
		keyBindings[0] = new KeyBinding("key.xpshardr.desc", Keyboard.KEY_RSHIFT, "key.endgamestuff.category");
		
		for (int i = 0; i < keyBindings.length; ++i) {
			ClientRegistry.registerKeyBinding(keyBindings[i]);
		}
		
		System.out.println("KEYBINDINGS ADDED!");
	}
}
