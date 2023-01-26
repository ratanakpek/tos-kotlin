package com.example.designpatterninkotlinjava.creational

import com.example.designpatterninkotlinjava.creational.factorymethod.FactoryData
import org.junit.Assert
import org.junit.Test

class FactoryPatternTest {

    @Test
    fun `Create object by method at runtime`() {
        val fd = FactoryData()
        Assert.assertEquals("Ios", fd.getPlatformByType("ios").loginWithDeviceType())
        Assert.assertEquals("Android", fd.getPlatformByType("and").loginWithDeviceType())
        Assert.assertEquals("Pc", fd.getPlatformByType("pc").loginWithDeviceType())
    }
}