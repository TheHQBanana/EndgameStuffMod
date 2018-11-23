package com.hqbanana.endgamestuffmod.fluids;

import javax.annotation.Nullable;

import com.hqbanana.endgamestuffmod.tileentities.TileEntityBase;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.capability.FluidTankPropertiesWrapper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

public class FluidStorageBase extends TileEntityBase implements IFluidTank, IFluidHandler {
    @Nullable
    protected FluidStack fluid;
    protected int maxFluid;
    protected TileEntity tile;
    protected boolean canFill = true;
    protected boolean canDrain = true;
    protected IFluidTankProperties[] tankProperties;
	
	public FluidStorageBase(String name, int maxFluid) {
        this(name, null, maxFluid);
    }

    public FluidStorageBase(String name, @Nullable FluidStack fluidStack, int maxFluid) {
    	super(name);
        this.fluid = fluidStack;
        this.maxFluid = maxFluid;
    }

    public FluidStorageBase(String name, Fluid fluid, int amount, int maxFluid) {
        this(name, new FluidStack(fluid, amount), maxFluid);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        if (!nbt.hasKey("Empty")) {
            FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt);
            setFluid(fluid);
        } else {
            setFluid(null);
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        if (fluid != null) {
            fluid.writeToNBT(nbt);
        } else {
            nbt.setString("Empty", "");
        }
        return nbt;
    }
    
    /* IFluidTank */
    @Override
    @Nullable
    public FluidStack getFluid() {
        return fluid;
    }

    public void setFluid(@Nullable FluidStack fluid) {
        this.fluid = fluid;
    }

    @Override
    public int getFluidAmount() {
        if (fluid == null) return 0;
        return fluid.amount;
    }

    @Override
    public int getCapacity() {
        return maxFluid;
    }

    public void setCapacity(int maxFluid) {
        this.maxFluid = maxFluid;
    }

    public void setTileEntity(TileEntity tile) {
        this.tile = tile;
    }

    @Override
    public FluidTankInfo getInfo() {
        return new FluidTankInfo(this);
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        if (this.tankProperties == null) {
        	 this.tankProperties = new IFluidTankProperties[] { new FluidTankPropertiesWrapper(new FluidTank(fluid, maxFluid)) };
        }
        return this.tankProperties;
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        if (!canFillFluidType(resource)) {
            return 0;
        }
        return fillInternal(resource, doFill);
    }

    /**
     * Use this method to bypass the restrictions from {@link #canFillFluidType(FluidStack)}
     * Meant for use by the owner of the tank when they have {@link #canFill() set to false}.
     */
    public int fillInternal(FluidStack resource, boolean doFill) {
        if (resource == null || resource.amount <= 0) {
            return 0;
        }

        if (!doFill) {
            if (fluid == null) {
                return Math.min(maxFluid, resource.amount);
            }

            if (!fluid.isFluidEqual(resource))  {
                return 0;
            }

            return Math.min(maxFluid - fluid.amount, resource.amount);
        }

        if (fluid == null) {
            fluid = new FluidStack(resource, Math.min(maxFluid, resource.amount));

            onContentsChanged();

            if (tile != null) {
                FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, tile.getWorld(), tile.getPos(), this, fluid.amount));
            }
            return fluid.amount;
        }

        if (!fluid.isFluidEqual(resource)) {
            return 0;
        }
        int filled = maxFluid - fluid.amount;

        if (resource.amount < filled) {
            fluid.amount += resource.amount;
            filled = resource.amount;
        }
        else {
            fluid.amount = maxFluid;
        }

        onContentsChanged();

        if (tile != null) {
            FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, tile.getWorld(), tile.getPos(), this, filled));
        }
        return filled;
    }

    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        if (!canDrainFluidType(getFluid())) {
            return null;
        }
        return drainInternal(resource, doDrain);
    }

    @Override
    public FluidStack drain(int maxDrain, boolean doDrain)  {
        if (!canDrainFluidType(fluid)) {
            return null;
        }
        return drainInternal(maxDrain, doDrain);
    }

    /**
     * Use this method to bypass the restrictions from {@link #canDrainFluidType(FluidStack)}
     * Meant for use by the owner of the tank when they have {@link #canDrain()} set to false}.
     */
    @Nullable
    public FluidStack drainInternal(FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(getFluid())) {
            return null;
        }
        return drainInternal(resource.amount, doDrain);
    }

    /**
     * Use this method to bypass the restrictions from {@link #canDrainFluidType(FluidStack)}
     * Meant for use by the owner of the tank when they have {@link #canDrain()} set to false}.
     */
    @Nullable
    public FluidStack drainInternal(int maxDrain, boolean doDrain) {
        if (fluid == null || maxDrain <= 0) {
            return null;
        }

        int drained = maxDrain;
        if (fluid.amount < drained) {
            drained = fluid.amount;
        }

        FluidStack stack = new FluidStack(fluid, drained);
        if (doDrain) {
            fluid.amount -= drained;
            if (fluid.amount <= 0) {
                fluid = null;
            }

            onContentsChanged();

            if (tile != null) {
                FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(fluid, tile.getWorld(), tile.getPos(), this, drained));
            }
        }
        return stack;
    }

    /**
     * Whether this tank can be filled with {@link IFluidHandler}
     *
     * @see IFluidTankProperties#canFill()
     */
    public boolean canFill() {
        return canFill;
    }

    /**
     * Whether this tank can be drained with {@link IFluidHandler}
     *
     * @see IFluidTankProperties#canDrain()
     */
    public boolean canDrain() {
        return canDrain;
    }

    /**
     * Set whether this tank can be filled with {@link IFluidHandler}
     *
     * @see IFluidTankProperties#canFill()
     */
    public void setCanFill(boolean canFill) {
        this.canFill = canFill;
    }

    /**
     * Set whether this tank can be drained with {@link IFluidHandler}
     *
     * @see IFluidTankProperties#canDrain()
     */
    public void setCanDrain(boolean canDrain) {
        this.canDrain = canDrain;
    }

    /**
     * Returns true if the tank can be filled with this type of fluid.
     * Used as a filter for fluid types.
     * Does not consider the current contents or capacity of the tank,
     * only whether it could ever fill with this type of fluid.
     *
     * @see IFluidTankProperties#canFillFluidType(FluidStack)
     */
    public boolean canFillFluidType(FluidStack fluid)
    {
        return canFill();
    }

    /**
     * Returns true if the tank can drain out this type of fluid.
     * Used as a filter for fluid types.
     * Does not consider the current contents or capacity of the tank,
     * only whether it could ever drain out this type of fluid.
     *
     * @see IFluidTankProperties#canDrainFluidType(FluidStack)
     */
    public boolean canDrainFluidType(@Nullable FluidStack fluid) {
        return fluid != null && canDrain();
    }

    protected void onContentsChanged() {

    }
}
