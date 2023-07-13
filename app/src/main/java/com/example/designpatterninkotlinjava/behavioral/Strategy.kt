package com.example.designpatterninkotlinjava.behavioral

interface Strategy {
    fun doOperation(num1: Int, num2: Int): Int
}

class OperationAdd : Strategy {
    override fun doOperation(num1: Int, num2: Int): Int {
        return num1 + num2
    }
}

class OperationSubtract : Strategy {
    override fun doOperation(num1: Int, num2: Int): Int {
        return num1 - num2
    }
}

class OperationMultiply : Strategy {
    override fun doOperation(num1: Int, num2: Int): Int {
        return num1 * num2
    }
}

class Context(private var strategy: Strategy? = null) {
    fun executeStrategy(num1: Int, num2: Int): Int {
        return strategy?.doOperation(num1, num2) ?: 0
    }
}