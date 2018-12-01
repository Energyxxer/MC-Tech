package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;

public class TagArgument implements SelectorArgument {
    private final String tag;
    private final boolean negated;

    public TagArgument(String tag) {
        if(tag.startsWith("!")) {
            this.tag = tag.substring(1);
            this.negated = true;
        } else {
            this.tag = tag;
            this.negated = false;
        }
    }

    public TagArgument(String tag, boolean negated) {
        this.tag = tag;
        this.negated = negated;
        if(!tag.matches(CommandUtils.IDENTIFIER_ALLOWED))
            throw new IllegalArgumentException("Tag argument value '" + tag + "' has illegal characters. Does not match regex: " + CommandUtils.IDENTIFIER_ALLOWED);
    }

    public String getTag() {
        return tag;
    }

    public boolean isNegated() {
        return negated;
    }

    @Override
    public String getArgumentString() {
        return "tag=" + (negated ? "!" : "") + tag;
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @Override
    public String toString() {
        return getArgumentString();
    }

    @Override
    public TagArgument clone() {
        return new TagArgument(tag, negated);
    }

    @Override
    public String getKey() {
        return "tag";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }
}
