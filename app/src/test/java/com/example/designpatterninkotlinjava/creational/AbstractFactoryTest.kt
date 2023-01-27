package com.example.designpatterninkotlinjava.creational

import com.example.designpatterninkotlinjava.creational.abstractfactory.Flutter
import com.example.designpatterninkotlinjava.creational.abstractfactory.MacButtonFactory
import com.example.designpatterninkotlinjava.creational.abstractfactory.WindowButtonFactory
import org.junit.Test

class AbstractFactoryTest {
    private fun configFlutterEnv(osType: String): Flutter {
        return Flutter(
            when (osType) {
                "mac" -> {
                    MacButtonFactory()
                }
                "win" -> {
                    WindowButtonFactory()
                }
                else -> {
                    throw IllegalArgumentException()
                }
            }
        )
    }

    @Test
    fun `The factory will provide component like button, checkbox by of their type for Mac OS`() {
        val flutterApp = configFlutterEnv("mac")
        println("mac")
        flutterApp.paint()
    }

    @Test
    fun `The factory will provide component like button, checkbox by of their type for Window OS`() {
        val flutterApp = configFlutterEnv("win")
        println("window")
        flutterApp.paint()
    }
}

