package com.example.designpatterninkotlinjava.behavior

import org.junit.Test

class CommandUnitTest {

    @Test
    fun commandTest() {
        val keyboardCommand = Keyboard()

        val pressA = PressOnLetterA(keyboardCommand)
        val pressB = PressOnLetterB(keyboardCommand)

        User().apply {
            addCommandByKeyboard(pressA)
            addCommandByKeyboard(pressB)
        }.also { it.sendCommandToSoftware() }
    }
}

//command
interface PressKeyboard {
    fun press()
}

class Keyboard {
    fun keyLetterA() {
        println("Key A")
    }

    fun keyLetterB() {
        println("Key B")
    }
}

//request wrap as object
class PressOnLetterA(var keyboard: Keyboard) : PressKeyboard {
    override fun press() {
        keyboard.keyLetterA()
    }
}

//request wrap as object
class PressOnLetterB(var keyboard: Keyboard) : PressKeyboard {
    override fun press() {
        keyboard.keyLetterB()
    }
}


class User {
    var presses = mutableListOf<PressKeyboard>()

    fun addCommandByKeyboard(pressKeyboard: PressKeyboard) {
        presses.add(pressKeyboard)
    }

    fun sendCommandToSoftware() {
        presses.forEach { it.press() }
        presses.clear()
    }
}