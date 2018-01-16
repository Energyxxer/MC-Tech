package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.types.DimensionType;

import static com.energyxxer.commodore.commands.execute.SubCommandResult.ExecutionChange.DIMENSION;

public class ExecuteInDimension implements ExecuteModifier {
    private DimensionType dimension;

    public ExecuteInDimension(DimensionType dimension) {
        this.dimension = dimension;
    }

    @Override
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult("in " + dimension, DIMENSION);
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
        return true;
    }
}