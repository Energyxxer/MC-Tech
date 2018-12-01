package com.energyxxer.commodore.functionlogic.score;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.commands.scoreboard.ObjectivesAddCommand;
import com.energyxxer.commodore.textcomponents.TextComponent;

import java.util.Objects;

public class Objective {
    private final ObjectiveManager parent;
    private String name;
    private String type;
    private TextComponent displayName;
    /**
     * Signifies whether the objective acts as a field, used to carry data from one tick or function to the next.
     * If this is true, a SET access command not followed by a GET access command at the end
     * of a function tree will not be removed.
     */
    private boolean field = false;

    public static final int MAX_NAME_LENGTH = 16;

    Objective(ObjectiveManager parent, String name, String type) {
        this(parent, name, type, null);
    }

    Objective(ObjectiveManager parent, String name, String type, TextComponent displayName) {
        this(parent, name, type, displayName, false);
    }

    Objective(ObjectiveManager parent, String name, String type, boolean field) {
        this(parent, name, type, null, field);
    }

    Objective(ObjectiveManager parent, String name, String type, TextComponent displayName, boolean field) {
        if(name.length() <= 0)
            throw new IllegalArgumentException("Objective name must not be empty");
        if(name.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException("Objective name '" + name + "' exceeds the limit of " + MAX_NAME_LENGTH + " characters");
        if(!name.matches(CommandUtils.IDENTIFIER_ALLOWED))
            throw new IllegalArgumentException("Objective name '" + name + "' has illegal characters. Does not match regex: " + CommandUtils.IDENTIFIER_ALLOWED);
        this.parent = parent;
        this.name = name;
        this.type = type;
        this.displayName = displayName;
        this.field = field;
    }

    public String getName() {
        return (parent.isPrefixEnabled() ? parent.getOwner().getPrefix() + "_" : "") + name;
    }

    public String getType() {
        return type;
    }

    public boolean isField() {
        return field;
    }

    public TextComponent getDisplayName() {
        return displayName;
    }

    public ObjectiveManager getParent() {
        return parent;
    }

    public ObjectivesAddCommand getObjectiveCreator() {
        return new ObjectivesAddCommand(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objective objective = (Objective) o;
        return Objects.equals(parent, objective.parent) &&
                Objects.equals(name, objective.name) &&
                Objects.equals(type, objective.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, name, type);
    }

    @Override
    public String toString() {
        return name + (!type.equals("dummy") ? " (" + type + ")" : "");
    }
}
