package com.energyxxer.commodore.commands.team;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.TeamReference;
import org.jetbrains.annotations.NotNull;

public class TeamCreateCommand extends TeamCommand {
    private final TeamReference reference;
    private final String displayName;

    public TeamCreateCommand(TeamReference reference) {
        this(reference, null);
    }

    public TeamCreateCommand(TeamReference reference, String displayName) {
        this.reference = reference;
        this.displayName = displayName;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "team add " + reference + ((displayName != null) ? " " + displayName : ""));
    }
}
