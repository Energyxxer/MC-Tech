package com.energyxxer.commodore.commands.teleport.destination;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.score.access.ScoreboardAccess;

import java.util.Collection;
import java.util.Collections;

public class EntityDestination implements TeleportDestination {
    private final Entity entity;

    public EntityDestination(Entity entity) {
        this.entity = entity;
    }

    @Override
    public String getRaw() {
        return "\be#";
    }

    @Override
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return entity.getScoreboardAccesses();
    }

    @Override
    public Collection<CommandEmbeddable> getEmbeddables() {
        return Collections.singletonList(entity);
    }
}