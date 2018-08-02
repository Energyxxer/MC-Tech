package com.energyxxer.commodore.entity;

import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.inspection.CommandEmbeddableResolution;
import com.energyxxer.commodore.inspection.EntityResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.MacroScoreHolder;
import com.energyxxer.commodore.score.ScoreHolder;
import com.energyxxer.commodore.score.access.ScoreboardAccess;

import java.util.Collection;

public interface Entity extends ScoreHolder, Cloneable, CommandEmbeddable {

    EntityResolution resolveFor(ExecutionContext context);

    @Override
    default CommandEmbeddableResolution resolveEmbed(ExecutionContext execContext) {
        return resolveFor(execContext).resolveEmbed(execContext);
    }

    void addMacroHolder(MacroScoreHolder macro);

    Collection<ScoreboardAccess> getScoreboardAccesses();

    int getLimit();

    Entity limitToOne();

    Entity clone();

    boolean isPlayer();

    default void assertPlayer() {
        if(!isPlayer()) throw new IllegalArgumentException("Provided entity '" + this + "' includes non-player entities, expected only players");
    }
}
