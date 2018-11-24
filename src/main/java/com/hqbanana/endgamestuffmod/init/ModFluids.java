package com.hqbanana.endgamestuffmod.init;

import com.hqbanana.endgamestuffmod.fluids.FluidBase;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids {
	public static final Fluid LIQUID_XP = new FluidBase("liquid_xp", new ResourceLocation("egsm:blocks/liquid_xp_still"), new ResourceLocation("egsm:blocks/liquid_xp_flow")).setAlpha(1.0f).setGaseous(false).setLuminosity(2).setTemperature(50);

	public static void registerFluids() {
		registerFluid(LIQUID_XP);
	}
	
	public static void registerFluid(Fluid fluid) {
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);
	}
}