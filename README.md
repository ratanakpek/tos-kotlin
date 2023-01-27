# Design Patterns In Kotlin & Java

Project maintained by [@ratanakpek](http://twitter.com/dbacinski) (Ratanak Pek)

Inspired by [@dbacinski](http://twitter.com/dbacinski) (Dariusz Baciński) & Phorn Borrom

## Table of Contents

* [Behavioral Patterns](#behavioral)
    * [Observer / Listener](#observer--listener)
    * [Strategy](#strategy)
    * [Command](#command)
    * [State](#state)
    * [Chain of Responsibility](#chain-of-responsibility)
    * [Visitor](#visitor)
    * [Mediator](#mediator)
    * [Memento](#memento)
* [Creational Patterns](#creational)
    * [Builder / Assembler](#builder--assembler)
    * [Factory Method](#factory-method)
    * [Singleton](#singleton)
    * [Abstract Factory](#abstract-factory)
* [Structural Patterns](#structural)
    * [Adapter](#adapter)
    * [Decorator](#decorator)
    * [Facade](#facade)
    * [Protection Proxy](#protection-proxy)
    * [Composite](#composite)

Creational Pattern
==========

[Singleton](app/src/main/java/com/example/designpatterninkotlinjava/creational/singleton/java/Coin.kt)
------------

The singleton pattern ensures that only one object of a particular class is ever created.
All further references to objects of the singleton class refer to the same underlying instance.
There are very few applications, do not overuse this pattern!

1. More specifically, the singleton pattern allows objects to:
    -   Ensure they only have one instance
    -   Provide easy access to that instance
    -   Control their instantiation (for example, hiding the constructors of a class)
    -   Thread safe

#### Example 1: Without class

```kotlin
object MyCoinSingleton {
    init {
        println("init")
    }
    var number: Int = 0
}
```
#### Example 2: With class
```kotlin
class Coin private constructor() {
    var number = 0
    companion object {
        val instance = Coin()
    }
}
```

#### Example 3: Other way for creating it
```kotlin
class LazySingleton private constructor() {

    companion object {
        private var instance: LazySingleton? = null

        // 1st way
        fun getInstance(): LazySingleton {
            if (instance == null) instance =
                LazySingleton()
            return instance!!
        }

        // 2nd way with inline class
        fun getSafeInstance(): LazySingleton {
            if (instance == null) {
                synchronized(LazySingleton::class.java) {
                    instance = LazySingleton()
                }
            }
            return instance!!
        }

        // 3rd way with @synchronized annotation
        @Synchronized
        fun getThirdInstance(): LazySingleton {
            return getInstance()
        }
    }
}
```

#### Usage

```kotlin
    @Test
    fun `Singleton with Java test`() {
        val coin1 = Coin.instance
        coin1.number = 10

        val coin2 = Coin.instance
        coin2.number = 100

        assertEquals(Coin.instance, coin1) //true
        assertEquals(Coin.instance, coin2) //true

        assertNotEquals(10, coin1.number) //true
        assertNotEquals(10, coin2.number) //true

        assertEquals(100, coin1.number) //true
        assertEquals(100, coin2.number) //true
    }
```

[Factory Method](app/src/main/java/com/example/designpatterninkotlinjava/creational/singleton/java/Coin.kt)
------------

Factory Method Pattern: is an interface or abstract class for creating an object but let the subclasses decide which class to instantiate. In other words, subclasses are responsible to create the instance of the class at runtime.
