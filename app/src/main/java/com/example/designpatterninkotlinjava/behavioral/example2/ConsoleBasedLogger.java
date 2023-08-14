package com.example.designpatterninkotlinjava.behavioral.example2;

public class ConsoleBasedLogger extends Logger {
    public ConsoleBasedLogger(int levels) {
        this.level = levels;
    }

    @Override
    protected void displayLogInfo(String msg) {
        System.out.println("Console INFO: " + msg);
    }
}
