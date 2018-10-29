package com.energyxxer.commodore.commands.data;

import com.energyxxer.commodore.commands.CommandDelegateResolution;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.nbt.path.NBTPath;

public class ModifySourceFromBlock implements ModifySource {
    private final CoordinateSet pos;
    private final NBTPath sourcePath;

    public ModifySourceFromBlock(CoordinateSet pos, NBTPath sourcePath) {
        this.pos = pos;
        this.sourcePath = sourcePath;
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("from block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + sourcePath);
    }
}