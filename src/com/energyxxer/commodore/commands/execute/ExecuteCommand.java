package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.functions.Function;

import java.util.ArrayList;

public class ExecuteCommand implements Command {
    private Command chainedCommand;

    private ArrayList<ExecuteModifier> modifiers = new ArrayList<>();

    public ExecuteCommand(Command chainedCommand) {
        this.chainedCommand = chainedCommand;
    }

    public void addModifier(ExecuteModifier modifier) {
        this.modifiers.add(modifier);
    }

    @Override
    public String getRawCommand(Entity sender) {
        StringBuilder sb = new StringBuilder("execute ");

        for(ExecuteModifier modifier : modifiers) {
            SubCommandResult result = modifier.getSubCommand(sender);
            sb.append(result.getSubCommand());
            sb.append(' ');
            if(result.getNewSender() != null) sender = result.getNewSender();
        }
        sb.append("run ");
        sb.append(chainedCommand.getRawCommand(sender));
        return sb.toString();
    }

    @Override
    public boolean isUsed() {
        return Command.super.isUsed() && chainedCommand.isUsed();
    }

    @Override
    public void onAppend(Function function) {
        Command.super.onAppend(function);
        chainedCommand.onAppend(function);
    }
}