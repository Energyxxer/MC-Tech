package com.energyxxer.commodore.commands.drop;

import com.energyxxer.commodore.types.Type;

import static com.energyxxer.commodore.types.TypeAssert.assertItem;

public class ToolOrHand {

    public static final ToolOrHand MAINHAND = new ToolOrHand("mainhand");
    public static final ToolOrHand OFFHAND = new ToolOrHand("offhand");

    final String name;

    private ToolOrHand(String name) {
        this.name = name;
    }

    public ToolOrHand(Type tool) {
        assertItem(tool);
        this.name = tool.toString();
    }

    @Override
    public String toString() {
        return name;
    }
}