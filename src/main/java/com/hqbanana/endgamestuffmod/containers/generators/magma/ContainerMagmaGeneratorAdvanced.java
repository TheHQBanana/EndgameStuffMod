package com.hqbanana.endgamestuffmod.containers.generators.magma;

import com.hqbanana.endgamestuffmod.containers.slots.SlotUpgradeEfficiency;
import com.hqbanana.endgamestuffmod.containers.slots.SlotUpgradeSpeed;
import com.hqbanana.endgamestuffmod.tileentities.generators.magma.TileEntityMagmaGeneratorAdvanced;

import net.minecraft.entity.player.InventoryPlayer;

public class ContainerMagmaGeneratorAdvanced extends ContainerMagmaGeneratorBase {
	public ContainerMagmaGeneratorAdvanced(InventoryPlayer player, TileEntityMagmaGeneratorAdvanced te) {
		super(player, te);
		this.addSlotToContainer(new SlotUpgradeSpeed(handler, 0, 176, 4));
		this.addSlotToContainer(new SlotUpgradeEfficiency(handler, 1, 176, 24));
	}
}
