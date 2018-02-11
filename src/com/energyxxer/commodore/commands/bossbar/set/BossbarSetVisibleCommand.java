package com.energyxxer.commodore.commands.bossbar.set;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.BossbarReference;
import org.jetbrains.annotations.NotNull;

public class BossbarSetVisibleCommand extends BossbarSetCommand {
    private boolean visible;

    public BossbarSetVisibleCommand(BossbarReference reference, boolean visible) {
        super(reference);
        this.visible = visible;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + visible);
    }
}