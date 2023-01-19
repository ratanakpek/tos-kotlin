package com.example.designpatterninkotlinjava.creational.singleton.java


class Coin private constructor() {
    var number = 0

    companion object {
        val instance = Coin()
    }
}

object MyCoinSingleton {
    init {
        println("init")
    }
    var number: Int = 0
}
