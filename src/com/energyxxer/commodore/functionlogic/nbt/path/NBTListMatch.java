package com.energyxxer.commodore.functionlogic.nbt.path;

import com.energyxxer.commodore.functionlogic.nbt.TagCompound;

public class NBTListMatch implements NBTPathNode {
    private final TagCompound listMatch;

    public NBTListMatch() {
        this(null);
    }

    public NBTListMatch(TagCompound listMatch) {
        this.listMatch = listMatch;
    }

    @Override
    public String getPathString() {
        return "[" + (listMatch != null ? listMatch.toHeadlessString() : "") + "]";
    }

    @Override
    public String getPathSeparator() {
        return "";
    }
}