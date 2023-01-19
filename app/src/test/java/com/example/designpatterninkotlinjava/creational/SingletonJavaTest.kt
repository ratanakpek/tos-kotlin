package com.example.designpatterninkotlinjava.creational

import com.example.designpatterninkotlinjava.creational.singleton.java.Coin
import com.example.designpatterninkotlinjava.creational.singleton.java.LazyInitializationSingleton
import org.junit.Test

import org.junit.Assert.*

class SingletonJavaTest {

    @Test
    fun `Singleton with Java test`() {
        val coin1 = Coin.instance
        coin1.number = 10
        println(coin1.number)
        println(coin1)

        val coin2 = Coin.instance
        coin2.number = 100
        println(coin2.number)
        println(coin2)

        println(Coin.instance.number)
        println(Coin.instance)
    }

    @Test
    fun `Lazy initialization test`() {
        println(LazyInitializationSingleton.getInstance())
    }

}