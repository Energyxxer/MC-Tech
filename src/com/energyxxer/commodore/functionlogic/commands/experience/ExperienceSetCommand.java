package com.energyxxer.commodore.functionlogic.commands.experience;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class ExperienceSetCommand extends ExperienceCommand {

    private final int amount;
    private final Unit unit;

    public ExperienceSetCommand(Entity player, int amount, Unit unit) {
        super(player);
        this.amount = amount;
        this.unit = unit;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "xp set \be0 " + amount + " " + unit.toString().toLowerCase(), player);
    }
}