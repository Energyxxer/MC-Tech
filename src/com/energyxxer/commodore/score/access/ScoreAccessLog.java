package com.energyxxer.commodore.score.access;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.functions.Function;
import com.energyxxer.commodore.score.MacroScore;

import java.util.ArrayList;
import java.util.Collection;

public class ScoreAccessLog {

    private final Function parent;

    private ArrayList<ScoreboardAccess> log = new ArrayList<>();

    public ScoreAccessLog(Function parent) {
        this.parent = parent;
    }

    public void filterCommand(Command command) {
        command.getScoreboardAccesses().forEach(this::filterAccess);
    }

    public void filterAccess(ScoreboardAccess access) {
        if(!log.contains(access)) log.add(access);
    }

    public void resolve() {

        MacroScoreAccessLog macroLog = new MacroScoreAccessLog();

        for(int i = log.size() - 1; i >= 0; i--) {
            ScoreboardAccess access = log.get(i);
            Collection<ScoreboardAccess> dependencies = access.getDependencies();

            if(access.getResolution() != ScoreboardAccess.AccessResolution.UNRESOLVED) continue;

            if(dependencies.size() > 0) {
                for(ScoreboardAccess dependency : dependencies) {
                    if(dependency.getResolution() == ScoreboardAccess.AccessResolution.UNRESOLVED) {
                        access.setResolution(ScoreboardAccess.AccessResolution.IN_PROCESS);

                        Function dependencyFunction = dependency.getFunction();
                        if(dependencyFunction == null)
                            throw new IllegalStateException("Dependency for access '" + access + " is not appended to a function");

                        dependencyFunction.getAccessLog().resolve();
                    }
                    if(dependency.getResolution() == ScoreboardAccess.AccessResolution.UNRESOLVED)
                        throw new RuntimeException("wtf dependency unresolved after resolve called");
                    if(dependency.getResolution() == ScoreboardAccess.AccessResolution.IN_PROCESS)
                        throw new RuntimeException("wtf dependency in process after resolve called");
                    if(dependency.getResolution() == ScoreboardAccess.AccessResolution.USED) access.setResolution(ScoreboardAccess.AccessResolution.USED);
                }
                if(access.getResolution() == ScoreboardAccess.AccessResolution.UNRESOLVED) access.setResolution(ScoreboardAccess.AccessResolution.UNUSED);
                if(access.getType() == ScoreboardAccess.AccessType.READ && access.getResolution() == ScoreboardAccess.AccessResolution.USED) {
                    macroLog.addUsed(access.getScores());
                } else {
                    macroLog.removeUsed(access.getScores());
                }
            } else if(access.getType() == ScoreboardAccess.AccessType.WRITE) {
                if(macroLog.areAnyUsed(access.getScores())) access.setResolution(ScoreboardAccess.AccessResolution.USED);
                else access.setResolution(ScoreboardAccess.AccessResolution.UNUSED);
                macroLog.removeUsed(access.getScores());
            } else if(access.getType() == ScoreboardAccess.AccessType.READ) {
                access.setResolution(ScoreboardAccess.AccessResolution.USED);
                macroLog.addUsed(access.getScores());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("- ");
        sb.append(parent);
        sb.append(" : Access Log");
        sb.append(" --------------\n");
        log.forEach(a -> {
            sb.append(a.getType());
            sb.append(" ");
            if(a.getDependencies() != null) {
                sb.append("⫘ (");
                sb.append(a.getDependencies());
                sb.append(") ");
            }
            sb.append("- ");
            sb.append(a.getResolution());
            sb.append('\n');
        });
        sb.append("----------------------------------");
        return sb.toString();
    }

}

class MacroScoreAccessLog {
    private ArrayList<MacroScore> usedMacroScores = new ArrayList<>();

    void addUsed(Collection<MacroScore> scores) {
        scores.forEach(s -> {
            if(!usedMacroScores.contains(s)) usedMacroScores.add(s);
        });
    }

    void removeUsed(Collection<MacroScore> scores) {
        usedMacroScores.removeAll(scores);
    }

    boolean areAnyUsed(Collection<MacroScore> scores) {
        for(MacroScore score : scores) {
            if(usedMacroScores.contains(score)) return true;
        }
        return false;
    }
}