package com.example.designpatterninkotlinjava.behavioral.example2;

public class ErrorBaseLogger extends Logger {

    public ErrorBaseLogger(int level) {
        this.level = level;
    }

    @Override
    protected void displayLogInfo(String msg) {
        System.out.println("ERROR INFO: " + msg);
    }
}
