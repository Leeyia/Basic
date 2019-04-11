package com.dintech.api.log;

public enum Level {

    ASSERT(7),
    DEBUG(3),
    ERROR(6),
    INFO(4),
    VERBOSE(2),
    WARN(5);

    private int mLevel;

    Level(int mLevel) {
        this.mLevel = mLevel;
    }

    public int getLevel() {
        return mLevel;
    }
}
