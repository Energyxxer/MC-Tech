package com.energyxxer.commodore.commands.bossbar.set;

import com.energyxxer.commodore.commands.bossbar.BossbarCommand;
import com.energyxxer.commodore.types.BossbarReference;

public abstract class BossbarSetCommand extends BossbarCommand {
    protected BossbarReference reference;

    public BossbarSetCommand(BossbarReference reference) {
        this.reference = reference;
    }

    protected String getBase() {
        return "bossbar set " + reference + " ";
    }
}