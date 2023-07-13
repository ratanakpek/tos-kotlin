package com.example.designpatterninkotlinjava.behavior

import com.example.designpatterninkotlinjava.behavioral.Context
import com.example.designpatterninkotlinjava.behavioral.OperationAdd
import com.example.designpatterninkotlinjava.behavioral.OperationMultiply
import com.example.designpatterninkotlinjava.behavioral.OperationSubtract
import org.junit.Test

class StrategyTest {

    @Test
    fun `Creating strategy operation test`() {
        val contextOperationAdd = Context(OperationAdd())
        println("5 + 5 = ${contextOperationAdd.executeStrategy(5, 5)}")

        val contextOperationSubtract = Context(OperationSubtract())
        println("5 - 5 = ${contextOperationSubtract.executeStrategy(5, 5)}")

        val contextOperationMultiply = Context(OperationMultiply())
        println("5 * 5 = ${contextOperationMultiply.executeStrategy(5, 5)}")
    }
}