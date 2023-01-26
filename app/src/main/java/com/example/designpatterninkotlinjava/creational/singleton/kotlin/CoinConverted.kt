package com.example.designpatterninkotlinjava.creational.singleton.kotlin

class CoinConverted private constructor() {
    var number = 0

    companion object {
        val instance = CoinConverted()
    }
}