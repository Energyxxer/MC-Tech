package com.energyxxer.commodore.functionlogic.commands.fill;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;

@Deprecated
public class FillOutlineCommand extends FillCommand {
    public FillOutlineCommand(CoordinateSet pos1, CoordinateSet pos2, Block block) {
        super(pos1, pos2, block, new FillOutlineMode());
    }
}
