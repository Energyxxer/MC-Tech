package com.energyxxer.commodore.functionlogic.commands.chunk;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ForceLoadCommand implements Command {
    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.forceload");
    }
}

abstract class ForceLoadChangeCommand extends ForceLoadCommand {
    @NotNull
    private final String subcommand;
    @NotNull
    private final CoordinateSet from;
    @Nullable
    private final CoordinateSet to;

    public ForceLoadChangeCommand(@NotNull String subcommand, @NotNull CoordinateSet from, @Nullable CoordinateSet to) {
        this.subcommand = subcommand;
        this.from = from;
        this.to = to;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        StringBuilder sb = new StringBuilder("forceload ");
        sb.append(subcommand);
        sb.append(' ');
        sb.append(from.getXZAs(Coordinate.DisplayMode.BLOCK_POS));
        if (to != null && (!from.getX().equals(to.getX()) || !from.getZ().equals(to.getZ()))) {
            sb.append(' ');
            sb.append(to.getXZAs(Coordinate.DisplayMode.BLOCK_POS));
        }
        return new CommandResolution(execContext, sb.toString());
    }
}