package com.energyxxer.commodore.commands.locate;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.StructureType;
import org.jetbrains.annotations.NotNull;

public class LocateCommand implements Command {
    private final StructureType structure;

    public LocateCommand(StructureType structure) {
        this.structure = structure;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "locate " + structure);
    }
}