package com.hqbanana.endgamestuffmod.containers.generators.coal;

import com.hqbanana.endgamestuffmod.containers.slots.SlotUpgrade;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorAdvanced;
import com.hqbanana.endgamestuffmod.util.Reference;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class ContainerCoalGeneratorAdvanced extends ContainerCoalGeneratorBase {
	public ContainerCoalGeneratorAdvanced(InventoryPlayer player, TileEntityCoalGeneratorAdvanced te) {
		super(player, te);
		this.addSlotToContainer(new SlotUpgrade(handler, 1, 176, 4));
		this.addSlotToContainer(new SlotUpgrade(handler, 2, 176, 24));
	}
}
