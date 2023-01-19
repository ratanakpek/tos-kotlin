package com.example.designpatterninkotlinjava.creational

import com.example.designpatterninkotlinjava.creational.singleton.java.MyCoinSingleton
import org.junit.Test


class SingletonKotlinTest {

    @Test
    fun `Lazy initialization test`() {
        println(MyCoinSingleton)
        println(MyCoinSingleton)

        MyCoinSingleton.number = 1
        println(MyCoinSingleton.number)

        println(MyCoinSingleton)

        MyCoinSingleton.number = 2
        println(MyCoinSingleton.number)
        println(MyCoinSingleton)
    }

}