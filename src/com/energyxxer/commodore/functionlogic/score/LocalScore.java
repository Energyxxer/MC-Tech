package com.energyxxer.commodore.functionlogic.score;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class LocalScore {
    @Nullable
    private final Objective objective;
    @Nullable
    private final ScoreHolder holder;

    public LocalScore(@Nullable ScoreHolder holder, @Nullable Objective objective) {
        this.objective = objective;
        this.holder = holder;
    }

    public LocalScore(@Nullable Objective objective, @Nullable ScoreHolder holder) {
        this.objective = objective;
        this.holder = holder;
    }

    @Nullable
    public Objective getObjective() {
        return objective;
    }

    @Nullable
    public ScoreHolder getHolder() {
        return holder;
    }

    public Collection<MacroScore> getMacroScores() {
        if(holder == null) return Collections.singletonList(new MacroScore(null, objective));
        ArrayList<MacroScore> list = new ArrayList<>();
        holder.getMacroHolders().forEach(h -> list.add(new MacroScore(h, objective)));
        return list;
    }

    @Override
    public String toString() {
        return "{" +
                "objective=" + objective +
                ", holder=" + holder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalScore that = (LocalScore) o;
        return Objects.equals(objective, that.objective) &&
                Objects.equals(holder, that.holder);
    }

    @Override
    public int hashCode() {

        return Objects.hash(objective, holder);
    }
}
