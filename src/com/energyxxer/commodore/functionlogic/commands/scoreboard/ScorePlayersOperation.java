package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ScorePlayersOperation implements Command {

    public enum Operation {
        ADD("+=", 0b1110),
        SUBTRACT("-=", 0b1110),
        MULTIPLY("*=", 0b1110),
        DIVIDE("/=", 0b1110),
        MODULO("%=", 0b1110),
        LESS_THAN("<", 0b1110),
        GREATER_THAN(">", 0b1110),
        ASSIGN("=", 0b0110),
        SWAP("><", 0b1111);
        //Leftmost 2 bits are for the read-access of target and source respectively.
        //Rightmost 2 bits are for write-access of target and source respectively.


        private final String shorthand;
        private final int accessMap;

        Operation(String shorthand, int accessMap) {
            this.shorthand = shorthand;
            this.accessMap = accessMap;
        }

        public String getShorthand() {
            return shorthand;
        }

        public Collection<ScoreboardAccess> getAccesses(LocalScore target, LocalScore source) {
            ArrayList<ScoreboardAccess> accesses = new ArrayList<>();
            ScoreboardAccess last = null;

            for(int i = 0b0001; i <= 0b1000; i <<= 1) {
                if((accessMap & i) > 0) {
                    ScoreboardAccess.AccessType accessType = (i > 0b0010) ? ScoreboardAccess.AccessType.READ : ScoreboardAccess.AccessType.WRITE;
                    LocalScore score = ((i & 0b1010) > 0) ? target : source;

                    last = new ScoreboardAccess(score.getMacroScores(), accessType, last);
                    accesses.add(last);
                }
            }

            Collections.reverse(accesses);

            return accesses;
        }

        public static Operation getOperationForSymbol(String symbol) {
            for(Operation value : values()) {
                if(value.shorthand.equals(symbol)) return value;
            }
            return null;
        }
    }

    private final LocalScore target;
    private final Operation operation;
    private final LocalScore source;

    private final ArrayList<ScoreboardAccess> accesses = new ArrayList<>();

    public ScorePlayersOperation(LocalScore target, Operation operation, LocalScore source) {
        this.target = target;
        this.operation = operation;
        this.source = source;

        if(target.getHolder() instanceof Entity) {
            accesses.addAll(((Entity) target.getHolder()).getScoreboardAccesses());
        }
        if(source.getHolder() instanceof Entity) {
            accesses.addAll(((Entity) source.getHolder()).getScoreboardAccesses());
        }

        accesses.addAll(operation.getAccesses(target, source));
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return accesses;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard players operation \be0 " + target.getObjective().getName() + " " + operation.getShorthand() + " \be1 " + source.getObjective().getName(), target.getHolder(), source.getHolder());
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
