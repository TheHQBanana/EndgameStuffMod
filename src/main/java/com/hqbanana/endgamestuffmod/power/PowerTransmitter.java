package com.hqbanana.endgamestuffmod.power;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class PowerTransmitter {
	private BlockPos bp;
	
	public PowerTransmitter(BlockPos bp) {
		this.bp = bp;
	}
	
	public void transmitEnergy (World world, CustomEnergyStorage ces) {
		int powerToTransmit = Math.min(ces.getMaxEnergyExtract(), ces.getEnergyStored());
		if (powerToTransmit > 0) {
			IEnergyStorage[] handlers = new IEnergyStorage[6];
			int[] wantedSide = new int[6];
			int accepted = 0;
			for (EnumFacing neighbour : EnumFacing.VALUES) {
				TileEntity te = world.getTileEntity(bp.offset(neighbour));
				EnumFacing from = neighbour.getOpposite();
				
				if (te == null) continue;
				
				IEnergyStorage handler = null;
				if (te.hasCapability(CapabilityEnergy.ENERGY, from)) {
					handler = te.getCapability(CapabilityEnergy.ENERGY, from);
					if (!handler.canReceive()) handler = null;
				}
				
				if (handler != null) {
					handlers[from.ordinal()] = handler;
					int wanted = handler.receiveEnergy(powerToTransmit, true);
					wantedSide[from.ordinal()] = wanted;
					accepted += wanted;
				}
			}
			
			if (accepted > 0) {
				for (EnumFacing from : EnumFacing.VALUES) {
					IEnergyStorage handler = handlers[from.ordinal()];
					int wanted = wantedSide[from.ordinal()];
					if (handler == null || wanted == 0) continue;
					
					int transmit = Math.min(Math.min(ces.getEnergyStored(), wanted), wanted * accepted / powerToTransmit);
					int received = Math.min(transmit, handler.receiveEnergy(transmit, false));
					ces.internalExtractEnergy(transmit, false);
					if (ces.getEnergyStored() <= 0) break;
				}
			}
		}
	}
}
