package com.energyxxer.commodore.functionlogic.commands.bossbar.set;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class BossbarSetMaxCommand extends BossbarSetCommand {
    private final int max;

    public BossbarSetMaxCommand(@NotNull Type bossbar, int max) {
        super(bossbar);

        if(max < 1) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Max value should not be less than 1, found " + max, max);

        this.max = max;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + "max " + max);
    }
}
