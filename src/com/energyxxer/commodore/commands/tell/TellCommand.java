package com.energyxxer.commodore.commands.tell;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class TellCommand implements Command {
    private Entity player;
    private String message;

    public TellCommand(Entity player, String message) {
        this.player = player;
        this.message = message;

        if(!player.isPlayer()) throw new IllegalArgumentException("Provided entity '" + player + "' includes non-player entities, expected only players");
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "tell \be0 " + message, player);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}