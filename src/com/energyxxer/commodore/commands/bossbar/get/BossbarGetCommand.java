package com.energyxxer.commodore.commands.bossbar.get;

import com.energyxxer.commodore.commands.bossbar.BossbarCommand;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.BossbarReference;
import org.jetbrains.annotations.NotNull;

public abstract class BossbarGetCommand extends BossbarCommand {
    protected BossbarReference reference;

    public BossbarGetCommand(BossbarReference reference) {
        this.reference = reference;
    }

    protected abstract String getKeyword();

    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "bossbar get " + reference + " " + getKeyword());
    }
}