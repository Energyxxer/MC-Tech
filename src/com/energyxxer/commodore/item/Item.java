package com.energyxxer.commodore.item;

import com.energyxxer.commodore.nbt.TagCompound;
import com.energyxxer.commodore.types.ItemType;

public class Item {
    private ItemType type;
    private TagCompound nbt;

    public Item(ItemType type) {
        this(type, null);
    }

    public Item(ItemType type, TagCompound nbt) {
        this.type = type;
        this.nbt = nbt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(type.toString());
        if(nbt != null) sb.append(nbt.toHeadlessString());
        return sb.toString();
    }
}