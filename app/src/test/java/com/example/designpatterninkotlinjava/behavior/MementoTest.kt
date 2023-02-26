package com.example.designpatterninkotlinjava.behavior

import com.example.designpatterninkotlinjava.behavioral.memento.Originator
import com.example.designpatterninkotlinjava.behavioral.memento.TextEditor
import org.junit.Assert
import org.junit.Test

class MementoTest {

    @Test
    fun memento() {
        val originator = Originator("initial state")
        val textEditor = TextEditor()
        textEditor.saveState(originator.createMemento())

        originator.state = "State #1"
        originator.state = "State #2"
        textEditor.saveState(originator.createMemento())

        originator.state = "State #3"
        println("Current State: " + originator.state)
        Assert.assertEquals(true, originator.state == "State #3")

        originator.restore(textEditor.restore(1))
        println("Second saved state: " + originator.state)
        Assert.assertEquals(true, originator.state == "State #2")

        originator.restore(textEditor.restore(0))
        println("First saved state: " + originator.state)
        Assert.assertEquals(true, originator.state == "initial state")
    }
}