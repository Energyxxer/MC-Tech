package com.energyxxer.commodore.functionlogic.commands.worldborder;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class WorldBorderSetDistance extends WorldBorderCommand {
    private final int distance;
    private final int time;

    public WorldBorderSetDistance(int distance) {
        this(distance, 0);
    }

    public WorldBorderSetDistance(int distance, int time) {
        this.distance = distance;
        this.time = time;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "worldborder set " + distance + ((time != 0) ? " " + time : ""));
    }
}