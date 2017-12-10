package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;

public class TagCommand implements Command {
    public enum Action {
        ADD, REMOVE
    }

    private Action action;
    private Entity entity;
    private String tag;

    public TagCommand(Action action, Entity entity, String tag) {
        this.action = action;
        this.entity = entity;
        this.tag = tag;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "tag " + action.toString().toLowerCase() + " " + entity.getSelectorAs(sender) + " " + tag;
    }
}