package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.NumericNBTType;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;

public class ExecuteStoreBlock extends ExecuteStore {
    private final CoordinateSet position;
    private final NBTPath path;
    private final NumericNBTType type;
    private final double scale;

    public ExecuteStoreBlock(CoordinateSet position, NBTPath path, NumericNBTType type) {
        this(StoreValue.DEFAULT, position, path, type);
    }

    public ExecuteStoreBlock(StoreValue storeValue, CoordinateSet position, NBTPath path, NumericNBTType type) {
        this(storeValue, position, path, type, 1);
    }

    public ExecuteStoreBlock(CoordinateSet position, NBTPath path, NumericNBTType type, double scale) {
        this(StoreValue.DEFAULT, position, path, type, scale);
    }

    public ExecuteStoreBlock(StoreValue storeValue, CoordinateSet position, NBTPath path, NumericNBTType type, double scale) {
        super(storeValue);
        this.position = position;
        this.path = path;
        this.type = type;
        this.scale = scale;
    }

    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "block " + position.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + path + " " + type.toString().toLowerCase() + " " + CommandUtils.numberToPlainString(scale));
    }

    @Override
    public boolean isIdempotent() {
        return true;
    }

    @Override
    public boolean isSignificant() {
        return true;
    }

    @Override
    public boolean isAbsolute() {
        return false;
    }
}