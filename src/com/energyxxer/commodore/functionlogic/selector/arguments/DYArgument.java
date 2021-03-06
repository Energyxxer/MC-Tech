package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

public class DYArgument implements SelectorArgument {

    private final double value;

    public DYArgument(double value) {
        this.value = value;
        assertFinite(value, "value");
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "dy=" + CommandUtils.numberToPlainString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @NotNull
    @Override
    public DYArgument clone() {
        return new DYArgument(value);
    }

    @NotNull
    @Override
    public String getKey() {
        return "dy";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.Y, ExecutionVariable.DIMENSION);
    }
}
