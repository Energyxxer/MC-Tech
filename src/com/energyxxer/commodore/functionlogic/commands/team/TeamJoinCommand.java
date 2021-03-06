package com.energyxxer.commodore.functionlogic.commands.team;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.defaults.TeamReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TeamJoinCommand extends TeamCommand {
    @NotNull
    private final TeamReference reference;
    @Nullable
    private final Entity entity;

    public TeamJoinCommand(@NotNull TeamReference reference) {
        this(reference, null);
    }

    public TeamJoinCommand(@NotNull TeamReference reference, @Nullable Entity entity) {
        this.reference = reference;
        this.entity = entity;
        if(entity != null) entity.assertScoreHolderFriendly();
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return (entity != null) ? new CommandResolution(execContext, "team join " + reference.toSafeString() + " " + entity.scoreHolderToString()) : new CommandResolution(execContext, "team join " + reference.toSafeString());
    }

    @Override
    public void assertAvailable() {
        if(entity != null) entity.assertAvailable();
    }
}
