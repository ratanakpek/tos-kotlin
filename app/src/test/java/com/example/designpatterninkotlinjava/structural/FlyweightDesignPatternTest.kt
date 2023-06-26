package com.example.designpatterninkotlinjava.structural

import org.junit.Test

class FlyweightDesignPatternTest {
    private val colors = arrayListOf("White", "Black", "Black")

    @Test
    fun `flyweight design test`() {
        val shapeFactory = ShapeFactory()
        colors.forEach {
            val circle = shapeFactory.getCircle(it)
            circle.draw()
        }
    }
}
