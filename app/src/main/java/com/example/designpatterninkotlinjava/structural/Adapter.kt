package com.example.designpatterninkotlinjava.structural

import kotlin.math.pow
import kotlin.math.sqrt

class RoundHole(var radius: Double) {
    fun fits(peg: RoundPeg): Boolean {
        return radius >= peg.radius
    }
}

/**
 * RoundPegs are compatible with RoundHoles.
 */
open class RoundPeg {
    open var radius: Double = 0.0
}

/**
 * SquarePegs are not compatible with RoundHoles (they were implemented by
 * previous development team). But we have to integrate them into our program.
 */
class SquarePeg(var width: Double) {
    fun getSquare() = this.width.pow(2.0)
}

class SquarePegAdapter(squarePeg: SquarePeg) : RoundPeg() {
    override var radius: Double = (sqrt((squarePeg.getSquare() / 2).pow(2.0) * 2))
}