package com.example.designpatterninkotlinjava.structural

interface Shape {
    fun draw()
}

class Circle(var x: Int, var y: Int, var color: String) : Shape {
    override fun draw() {
        println("Color: $color, X : $x, Y : $y")
    }
}

class ShapeFactory {
    var circleMap = HashMap<String, Shape>()

    fun getCircle(color: String): Shape {
        var circle = circleMap[color]
        return if (circle == null) {
            circle = Circle(getRandomXY(), getRandomXY(), color)
            circleMap[color] = circle
            circle
        } else circle
    }

    private fun getRandomXY() = (Math.random() * 100).toInt()
}

