package com.example.designpatterninkotlinjava.behavioral.state.java;

public class StartState implements State {
    @Override
    public void doAction(Context context) {
        context.setState(this);
        System.out.println("Player is in start state");
    }

    public String toString() {
        return "Start State";
    }
}
