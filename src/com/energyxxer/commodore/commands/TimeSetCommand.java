package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;

public class TimeSetCommand extends TimeCommand {
    public enum TimeOfDay {
        DAY(1000), MIDNIGHT(18000), NIGHT(13000), NOON(6000);
        private int time;

        TimeOfDay(int time) {
            this.time = time;
        }
    }

    private int time;

    public TimeSetCommand(int time) {
        this.time = time;
    }

    public TimeSetCommand(TimeOfDay time) {
        this(time.time);
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "time set " + time;
    }
}