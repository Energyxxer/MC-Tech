package com.energyxxer.commodore.commands.schedule;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.functions.FunctionSection;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.FunctionReference;
import com.energyxxer.commodore.util.TimeSpan;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public class ScheduleCommand implements Command {

    private final Type function;
    private ExecutionContext execContext = null;
    private Collection<ScoreboardAccess> accesses = null;

    private TimeSpan delay;

    public ScheduleCommand(Function function, TimeSpan delay) {
        this(new FunctionReference(function), delay);
    }

    public ScheduleCommand(FunctionReference function, TimeSpan delay) {
        this.function = function;
        this.delay = delay;

        if(delay.getTicks() == 0) throw new IllegalArgumentException("Cannot schedule for the same tick");
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "schedule function " + function + "");
    }

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        if(execContext == null)
            throw new IllegalStateException("Cannot resolve scoreboard accesses for unappended function command");
        if(accesses != null) return accesses;
        if(function instanceof FunctionReference) {
            ((FunctionReference) function).getFunction().resolveAccessLogs();
            accesses = ((FunctionReference) function).getFunction().getScoreboardAccesses(execContext);
        }
        if(accesses == null) accesses = Collections.emptyList();
        return accesses;
    }

    @Override
    public void onAppend(FunctionSection section, ExecutionContext execContext) {
        this.execContext = execContext;
        Command.super.onAppend(section, execContext);
    }
}