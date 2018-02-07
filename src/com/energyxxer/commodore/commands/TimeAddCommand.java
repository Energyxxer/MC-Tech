package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class TimeAddCommand extends TimeCommand {
    private int amount;

    public TimeAddCommand(int amount) {
        this.amount = amount;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "time add " + amount);
    }
}
