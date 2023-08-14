package com.example.designpatterninkotlinjava.behavioral.example2;

public class DebugBasedLogger extends Logger {
    public DebugBasedLogger(int level) {
        this.level = level;
    }

    @Override
    protected void displayLogInfo(String msg) {
        System.out.println("DEBUG INFO: " + msg);
    }
}
