package com.example.designpatterninkotlinjava.structural

import com.example.designpatterninkotlinjava.structural.decorator.AddColorOSToOriginal
import com.example.designpatterninkotlinjava.structural.decorator.Nougat
import com.example.designpatterninkotlinjava.structural.decorator.Oreo
import org.junit.Test

class DecoratorTest {
    @Test
    fun `Decorator with sample`() {
        println("Nougat with original from Google")
        val nougat = Nougat()
        nougat.runOsVersion()

        val customNougat = AddColorOSToOriginal(Nougat())
        println("\nNougat with original from Google with color OS System!")
        customNougat.runOsVersion()


        val customOreo = AddColorOSToOriginal(Oreo())
        println("\nOreo with original from Google with color OS System!")
        customOreo.runOsVersion()
    }
}
