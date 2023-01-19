package com.example.designpatterninkotlinjava.creational

import com.example.designpatterninkotlinjava.creational.singleton.java.Coin
import com.example.designpatterninkotlinjava.creational.singleton.java.LazyInitializationSingleton
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

class SingletonJavaTest {

    @Test
    fun `Singleton with Java test`() {
        val coin1 = Coin.instance
        coin1.number = 10

        val coin2 = Coin.instance
        coin2.number = 100

        assertEquals(Coin.instance, coin1)
        assertEquals(Coin.instance, coin2)

        assertNotEquals(10, coin1.number)
        assertNotEquals(10, coin2.number)

        assertEquals(100, coin1.number)
        assertEquals(100, coin2.number)
    }

    @Test
    fun `Lazy initialization test`() {
        val singleOne = LazyInitializationSingleton.getInstance()
        val singleTwo = LazyInitializationSingleton.getInstance()
        assertEquals(singleOne, singleTwo)
    }

}