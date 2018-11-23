package com.hqbanana.endgamestuffmod.tileentities.generators.coal;

import net.minecraftforge.items.ItemStackHandler;

public class TileEntityCoalGeneratorSimple extends TileEntityCoalGeneratorBase {
	public TileEntityCoalGeneratorSimple() {
		super("Simple coal generator", 100000, 0, 100, 0);
		inventory = new ItemStackHandler(1) {
			@Override
			protected void onContentsChanged(int slot) {
				super.onContentsChanged(slot);
				TileEntityCoalGeneratorSimple.this.markDirty();
			};
		};
	}
}
