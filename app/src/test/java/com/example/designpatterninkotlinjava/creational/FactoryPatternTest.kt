package com.example.designpatterninkotlinjava.creational

import com.example.designpatterninkotlinjava.creational.factorymethod.FactoryData
import com.example.designpatterninkotlinjava.creational.factorymethod.generateAppByType
import org.junit.Assert
import org.junit.Test

class FactoryPatternTest {

    //Factory Method is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.
    @Test
    fun `Create object by method at runtime without interface`() {
        val fd = FactoryData()
        Assert.assertEquals("Ios", fd.getPlatformByType("ios").loginWithDeviceType())
        Assert.assertEquals("Android", fd.getPlatformByType("and").loginWithDeviceType())
        Assert.assertEquals("Pc", fd.getPlatformByType("pc").loginWithDeviceType())
    }


    @Test
    fun `Create object by method at runtime with interface`() {
        //Android
        val runOnType = generateAppByType("AND")
        //generate app logic
        runOnType.runTheApp()
        Assert.assertEquals("apk", runOnType.generateAppNow().generateAppFile())


        // IOS
        val runOnTypeIos = generateAppByType("IOS")
        //generate app logic
        runOnTypeIos.runTheApp()
        Assert.assertEquals("ipk", runOnTypeIos.generateAppNow().generateAppFile())

    }
}