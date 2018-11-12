package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;

public class DataRemoveCommand extends DataCommand {

    private final NBTPath path;

    public DataRemoveCommand(Entity entity, NBTPath path) {
        super(entity);
        this.path = path;
    }

    public DataRemoveCommand(CoordinateSet pos, NBTPath path) {
        super(pos);
        this.path = path;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        if(entity != null) return new CommandResolution(execContext, "data remove entity \be0 " + path, entity);
        return new CommandResolution(execContext, "data remove block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + path);
    }
}