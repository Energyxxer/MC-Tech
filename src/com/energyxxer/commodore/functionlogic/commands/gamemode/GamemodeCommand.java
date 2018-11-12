package com.energyxxer.commodore.functionlogic.commands.gamemode;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

import static com.energyxxer.commodore.types.TypeAssert.assertGamemode;

public class GamemodeCommand implements Command {

    private final Type gamemode;
    private final Entity player;

    public GamemodeCommand(Type gamemode, Entity player) {
        this.gamemode = gamemode;
        this.player = player;

        assertGamemode(gamemode);
        player.assertPlayer();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "gamemode " + gamemode + " \be0", player);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}