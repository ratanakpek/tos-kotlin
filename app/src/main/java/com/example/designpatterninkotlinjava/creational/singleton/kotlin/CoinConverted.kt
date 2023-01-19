package com.example.designpatterninkotlinjava.creational.singleton.kotlin

import com.example.designpatterninkotlinjava.creational.singleton.kotlin.CoinConverted

class CoinConverted private constructor() {
    var number = 0

    companion object {
        val instance = CoinConverted()
    }
}