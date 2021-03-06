package com.energyxxer.commodore.functionlogic.commands.teleport.facing;

import com.energyxxer.commodore.functionlogic.commands.execute.EntityAnchor;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class EntityFacing implements TeleportFacing {
    @NotNull
    private final Entity entity;
    @NotNull
    private final EntityAnchor anchor;

    public EntityFacing(@NotNull Entity entity) {
        this(entity, EntityAnchor.FEET);
    }

    public EntityFacing(@NotNull Entity entity, @NotNull EntityAnchor anchor) {
        this.entity = entity;
        this.anchor = anchor;

        entity.assertSingle();
        entity.assertEntityFriendly();
    }

    @NotNull
    @Override
    public String getRaw() {
        return "facing entity " + entity + " " + anchor.toString().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public void assertAvailable() {
        entity.assertAvailable();
    }
}
