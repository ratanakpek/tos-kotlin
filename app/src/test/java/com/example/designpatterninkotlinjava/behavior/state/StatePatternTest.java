package com.example.designpatterninkotlinjava.behavior.state;

import com.example.designpatterninkotlinjava.behavioral.state.java.Context;
import com.example.designpatterninkotlinjava.behavioral.state.java.StartState;
import com.example.designpatterninkotlinjava.behavioral.state.java.StopState;

import org.junit.Test;

public class StatePatternTest {
    @Test
    public void statePatternTest() {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}
