package com.example.designpatterninkotlinjava.behavioral.example2;

public abstract class Logger {
    public static int OUTPUTINFO = 1;
    public static int ERRORINFO = 2;
    public static int DEBUGINFO = 3;

    protected int level;
    protected Logger nextLevelLogger;

    public void setNextLevelLogger(Logger nextLevelLogger) {
        this.nextLevelLogger = nextLevelLogger;
    }

    public void logMessage(int level, String msg) {
        if (this.level <= level) {
            displayLogInfo(msg);
        }
        if (nextLevelLogger != null) {
            nextLevelLogger.logMessage(level, msg);
        }
    }

    protected abstract void displayLogInfo(String msg);
}
