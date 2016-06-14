package com.android.core.control.logcat;

/**
 * @author: 蜡笔小新
 * @date: 2016-06-02 17:24
 * @GitHub: https://github.com/meikoz
 */
public final class Settings {

    private int methodCount = 2;
    private boolean showThreadInfo = true;
    private int methodOffset = 0;
    private LogTool logTool;
    private LogLevel logLevel;

    public Settings() {
        this.logLevel = LogLevel.FULL;
    }

    public Settings hideThreadInfo() {
        this.showThreadInfo = false;
        return this;
    }

    public Settings methodCount(int methodCount) {
        if(methodCount < 0) {
            methodCount = 0;
        }

        this.methodCount = methodCount;
        return this;
    }

    public Settings logLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    public Settings methodOffset(int offset) {
        this.methodOffset = offset;
        return this;
    }

    public Settings logTool(LogTool logTool) {
        this.logTool = logTool;
        return this;
    }

    public int getMethodCount() {
        return this.methodCount;
    }

    public boolean isShowThreadInfo() {
        return this.showThreadInfo;
    }

    public LogLevel getLogLevel() {
        return this.logLevel;
    }

    public int getMethodOffset() {
        return this.methodOffset;
    }

    public LogTool getLogTool() {
        if(this.logTool == null) {
            this.logTool = new AndroidLogTool();
        }

        return this.logTool;
    }
}
