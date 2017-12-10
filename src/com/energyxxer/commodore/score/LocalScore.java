package com.energyxxer.commodore.score;

import com.energyxxer.commodore.score.access.ScoreAccessLog;

public class LocalScore {
    private Objective objective;
    private ScoreManager parent;

    private ScoreAccessLog accessLog = new ScoreAccessLog(this);

    public LocalScore(Objective objective, ScoreManager parent) {
        this.objective = objective;
        this.parent = parent;

        objective.getParent().registerLocalScore(this);
    }

    public Objective getObjective() {
        return objective;
    }

    public ScoreManager getParent() {
        return parent;
    }

    public ScoreAccessLog getAccessLog() {
        return accessLog;
    }

    @Override
    public String toString() {
        return "{" +
                "objective=" + objective +
                ", holder=" + parent.getHolder() +
                '}';
    }
}