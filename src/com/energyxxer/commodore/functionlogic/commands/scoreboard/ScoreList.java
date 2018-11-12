package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class ScoreList implements Command {
    private final Entity entity;

    public ScoreList() {
        this(null);
    }

    public ScoreList(Entity entity) {
        this.entity = entity;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return (entity != null) ? new CommandResolution(execContext, "scoreboard players list \be0", entity) : new CommandResolution(execContext, "scoreboard players list");
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}