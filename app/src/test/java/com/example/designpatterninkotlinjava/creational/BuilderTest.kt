package com.example.designpatterninkotlinjava.creational

import com.example.designpatterninkotlinjava.creational.builder.javastyle.BuildComputer
import com.example.designpatterninkotlinjava.creational.builder.kotlinstyle.SetupComputer
import org.junit.Assert
import org.junit.Test

class BuilderTest {

    @Test
    fun `Create builder pattern with java coding style`() {
        val buildComputer = BuildComputer.Builder()
            .addRam("Dell Ram")
            .addCpu("Cpu of Lenovo")
            .addKeyboard("Magic Keyboard")
            .addMouse("Logitech")
            .build()

        Assert.assertEquals("Dell Ram", buildComputer.ram)//true
        Assert.assertEquals("Cpu of Lenovo", buildComputer.cpu)//true
        Assert.assertEquals("Magic Keyboard", buildComputer.keyboard)//true
        Assert.assertEquals("Logitech", buildComputer.mouse)//true
    }

    @Test
    fun `Create builder pattern with kotlin coding style`() {
        val setupComputer = SetupComputer.Builder()
            .addRam("Dell Ram")
            .addCpu("Cpu of Lenovo")
            .addKeyboard("Magic Keyboard")
            .build()

        Assert.assertEquals("Dell Ram", setupComputer.ram)//true
        Assert.assertEquals("Cpu of Lenovo", setupComputer.cpu)//true
        Assert.assertEquals("Magic Keyboard", setupComputer.keyboard)//true

    }
}