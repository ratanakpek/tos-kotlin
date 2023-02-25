package com.example.designpatterninkotlinjava.behavior.mediator

import com.example.designpatterninkotlinjava.behavioral.mediator.java.MediatorPhone
import com.example.designpatterninkotlinjava.behavioral.mediator.java.MonitorDisplay
import com.example.designpatterninkotlinjava.behavioral.mediator.java.PowerButton
import org.junit.Test

class MediatorTest {

    @Test
    fun `If we don't use MediatorPhone class, the Phone & monitor will couple to each other`() {
        MediatorPhone(
            PowerButton(),
            MonitorDisplay()
        ).powerOnPhone()
    }
}

