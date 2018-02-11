package com.energyxxer.commodore.commands.difficulty;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.DifficultyType;

public class DifficultySetCommand extends DifficultyCommand {

    private DifficultyType difficulty;

    public DifficultySetCommand(DifficultyType difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "difficulty " + difficulty);
    }
}