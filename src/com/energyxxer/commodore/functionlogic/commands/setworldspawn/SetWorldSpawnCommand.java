package com.energyxxer.commodore.functionlogic.commands.setworldspawn;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class SetWorldSpawnCommand implements Command {

    private final CoordinateSet pos;

    public SetWorldSpawnCommand(CoordinateSet pos) {
        this.pos = pos;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "setworldspawn " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS));
    }
}