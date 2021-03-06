package com.energyxxer.commodore.functionlogic.commands.effect;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.energyxxer.commodore.types.TypeAssert.assertEffect;

public class EffectClearCommand extends EffectCommand {
    @Nullable
    private final Type type;

    public EffectClearCommand() {
        this(null);
    }

    public EffectClearCommand(@Nullable Entity entity) {
        this(entity, null);
    }

    public EffectClearCommand(@Nullable Entity entity, @Nullable Type type) {
        super(entity != null ? entity : new Selector(Selector.BaseSelector.SENDER));
        this.type = type;

        if(type != null) assertEffect(type);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext,
                VersionFeatureManager.getBoolean("command.effect.explicit") ?
                        ("effect clear " + entity + ((type != null) ? " " + type.toSafeString() : "")) :
                        ("effect " + entity + " " + (type != null ? type.toSafeString() + " 0" : "clear"))
        );
    }
}
