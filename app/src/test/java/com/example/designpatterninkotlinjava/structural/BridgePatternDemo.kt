package com.example.designpatterninkotlinjava.structural

import org.junit.Test

class BridgePatternDemo {

    @Test
    fun initDemo() {
        val vehicle1 = Car(Produce(), Assemble())
        vehicle1.manufacture()
        val vehicle2 = Bike(Produce(), Assemble())
        vehicle2.manufacture()
    }
}