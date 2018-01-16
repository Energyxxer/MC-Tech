package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.Objective;

public class TriggerCommand implements Command {

    public enum Action {
        SET, ADD
    }

    private Objective objective;
    private Action action;
    private int amount;

    public TriggerCommand(Objective objective) {
        this(objective, Action.ADD, 1);
    }

    public TriggerCommand(Objective objective, Action action, int amount) {
        if(!objective.getType().equals("trigger"))
            throw new IllegalArgumentException("Unable to use objective '" + objective.getName() + "' with TriggerCommand; Expected objective of blockType 'trigger', instead got '" + objective.getType() + "'");
        this.objective = objective;
        this.action = action;
        this.amount = amount;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "trigger " + objective.getName() + (action != Action.ADD || amount != 1 ? " " + action.toString().toLowerCase() + " " + amount : "");
    }
}