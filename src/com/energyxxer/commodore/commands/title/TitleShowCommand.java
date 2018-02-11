package com.energyxxer.commodore.commands.title;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.textcomponents.TextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class TitleShowCommand extends TitleCommand {
    public enum Display {
        TITLE, SUBTITLE, ACTIONBAR
    }

    private Display display;
    private TextComponent message;

    public TitleShowCommand(Entity player, Display display, TextComponent message) {
        super(player);
        this.display = display;
        this.message = message;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        String raw = message.toString();
        Collection<CommandEmbeddable> embeddables = message.getEmbeddables();
        for(int i = 1; i <= embeddables.size(); i++) {
            raw = raw.replaceFirst("\be#", "\be" + i);
        }
        ArrayList<CommandEmbeddable> allEmbeddables = new ArrayList<>(embeddables);
        allEmbeddables.add(0, player);
        return new CommandResolution(execContext, "title \be0 " + display.toString().toLowerCase() + " " + raw, allEmbeddables);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        ArrayList<ScoreboardAccess> accesses = new ArrayList<>(player.getScoreboardAccesses());
        accesses.addAll(message.getScoreboardAccesses());
        return accesses;
    }
}