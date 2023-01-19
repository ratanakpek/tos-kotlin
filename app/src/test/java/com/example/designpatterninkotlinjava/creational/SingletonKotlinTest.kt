package com.example.designpatterninkotlinjava.creational

import com.example.designpatterninkotlinjava.creational.singleton.java.MyCoinSingleton
import com.example.designpatterninkotlinjava.creational.singleton.kotlin.LazySingleton
import org.junit.Assert
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

    @Test
    fun `Is method access has same object test`() {
        Assert.assertEquals(LazySingleton.getInstance(), LazySingleton.getSafeInstance())
        Assert.assertEquals(LazySingleton.getThirdInstance(), LazySingleton.getSafeInstance())
        Assert.assertEquals(LazySingleton.getSafeInstance(), LazySingleton.getThirdInstance())
    }

}