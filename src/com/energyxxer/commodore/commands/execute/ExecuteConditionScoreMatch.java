package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.selector.SelectorNumberArgument;

public class ExecuteConditionScoreMatch extends ExecuteCondition {

    private LocalScore target;
    private SelectorNumberArgument range;

    public ExecuteConditionScoreMatch(ConditionType flowController, LocalScore target, SelectorNumberArgument range) {
        super(flowController);
        this.target = target;
        this.range = range;
    }

    @Override
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult(this.getStarter() + "score " + CommandUtils.getRawReference(target.getHolder(), sender) + " " + target.getObjective().getName() + " matches " + range.toString());
    }

    @Override
    public boolean isIdempotent() {
        return true;
    }

    @Override
    public boolean isSignificant() {
        return true;
    }

    @Override
    public boolean isAbsolute() {
        return false;
    }
}