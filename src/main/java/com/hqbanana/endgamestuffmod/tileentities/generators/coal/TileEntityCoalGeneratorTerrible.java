package com.hqbanana.endgamestuffmod.tileentities.generators.coal;

import net.minecraftforge.items.ItemStackHandler;

public class TileEntityCoalGeneratorTerrible extends TileEntityCoalGeneratorBase {
	private ItemStackHandler inventory = new ItemStackHandler(1) {
		@Override
		protected void onContentsChanged(int slot) {
			super.onContentsChanged(slot);
			TileEntityCoalGeneratorTerrible.this.markDirty();
		};
	};
	
	public TileEntityCoalGeneratorTerrible() {
		super("Terrible coal generator", 1, 100000, 0, 100, 0);
	}
}
