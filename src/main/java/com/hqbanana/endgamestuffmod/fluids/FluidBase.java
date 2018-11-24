package com.hqbanana.endgamestuffmod.fluids;

import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FluidBase extends Fluid {
	 protected static int mapColor = 0xFFFFFFFF;
	 protected static float overlayAlpha = 0.2F;
	 protected static SoundEvent emptySound = SoundEvents.ITEM_BUCKET_EMPTY;
	 protected static SoundEvent fillSound = SoundEvents.ITEM_BUCKET_FILL;
	 protected static Material material = Material.WATER;
	    
	public FluidBase(String name, ResourceLocation still, ResourceLocation flow) {
		super(name, still, flow);
		this.setUnlocalizedName(name);
	}
	
	public FluidBase(String name, ResourceLocation still, ResourceLocation flow, int color) {
		this(name, still, flow);
		setColor(color);
	}
	
	public FluidBase(String name, ResourceLocation still, ResourceLocation flow, int color, float overlayAlpha) {
		this(name, still, flow, color);
		setAlpha(overlayAlpha);
	}
	
	@Override
	public int getColor() {
		return super.getColor();
	}
	
	@Override
	public Fluid setColor(int color) {
		return super.setColor(color);
	}
	
	public float getAlpha() {
		return overlayAlpha;
	}
	
	public FluidBase setAlpha(float overlayAlpha) {
		this.overlayAlpha = overlayAlpha;
		return this;
	}
	
	@Override
	public Fluid setEmptySound(SoundEvent emptySound) {
		this.emptySound = emptySound;
		return this;
	}
	
	@Override
	public SoundEvent getEmptySound() {
		return this.emptySound;
	}
	
	@Override
	public Fluid setFillSound(SoundEvent fillSound) {
		this.fillSound = fillSound;
		return this;
	}
	
	@Override
	public SoundEvent getFillSound() {
		return this.fillSound;
	}
	
	public FluidBase setMaterial(Material material) {
		this.material = material;
		return this;
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	@Override
	public boolean doesVaporize(FluidStack fluidStack) {
		if (block == null) return false;
		return block.getDefaultState().getMaterial() == getMaterial();
	}
}
