package com.energyxxer.commodore.functionlogic.commands.clone;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class CloneCommand implements Command {

    public enum SourceMode {
        NORMAL, FORCE, MOVE;

        public static final SourceMode DEFAULT = NORMAL;
    }

    protected final CoordinateSet source1;
    protected final CoordinateSet source2;

    protected final CoordinateSet destination;

    protected final SourceMode sourceMode;

    public CloneCommand(CoordinateSet source1, CoordinateSet source2, CoordinateSet destination) {
        this(source1, source2, destination, SourceMode.DEFAULT);
    }

    public CloneCommand(CoordinateSet source1, CoordinateSet source2, CoordinateSet destination, SourceMode sourceMode) {
        this.source1 = source1;
        this.source2 = source2;
        this.destination = destination;
        this.sourceMode = sourceMode;
    }

    private String getBase() {
        return "clone " + source1.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + source2.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + destination.getAs(Coordinate.DisplayMode.BLOCK_POS);
    }

    protected String getMaskExtra() {
        return (sourceMode != SourceMode.DEFAULT) ? " replace" : "";
    }

    private String getSourceModeExtra() {
        return (sourceMode != SourceMode.DEFAULT) ? " " + sourceMode.toString().toLowerCase() : "";
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + getMaskExtra() + getSourceModeExtra());
    }
}