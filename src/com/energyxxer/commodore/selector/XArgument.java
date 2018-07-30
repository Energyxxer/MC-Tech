package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class XArgument implements SelectorArgument {

    private final double value;

    public XArgument(double value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "x=" + CommandUtils.toString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public XArgument clone() {
        return new XArgument(value);
    }

    @Override
    public String getKey() {
        return "x";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }
}
