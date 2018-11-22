package com.hqbanana.endgamestuffmod.containers.generators.coal;

import com.hqbanana.endgamestuffmod.containers.slots.SlotUpgradeEfficiency;
import com.hqbanana.endgamestuffmod.containers.slots.SlotUpgradeSpeed;
import com.hqbanana.endgamestuffmod.tileentities.generators.coal.TileEntityCoalGeneratorAdvanced;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerCoalGeneratorAdvanced extends ContainerCoalGeneratorBase {
	public ContainerCoalGeneratorAdvanced(InventoryPlayer player, TileEntityCoalGeneratorAdvanced te) {
		super(player, te);
		this.addSlotToContainer(new SlotUpgradeSpeed(handler, 1, 176, 4));
		this.addSlotToContainer(new SlotUpgradeEfficiency(handler, 2, 176, 24));
	}
}
