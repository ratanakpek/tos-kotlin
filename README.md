# Design Patterns In Kotlin & Java

Project maintained by [@ratanakpek](http://twitter.com/dbacinski) (Ratanak Pek)

Inspired by [@dbacinski](http://twitter.com/dbacinski) (Dariusz Baciński) & Phorn Borrom

## Table of Contents

* [Creational Patterns](#creational)
    * [Builder / Assembler](#builder--assembler)
    * [Factory Method](#factory-method)
    * [Singleton](#singleton)
    * [Abstract Factory](#abstract-factory)

* [Behavioral Patterns](#behavioral)
    * [Observer / Listener](#observer--listener)
    * [Strategy](#strategy)
    * [Command](#command)
    * [State](#state)
    * [Chain of Responsibility](#chain-of-responsibility)
    * [Visitor](#visitor)
    * [Mediator](#mediator)
    * [Memento](#memento)

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

[Factory Method](app/src/main/java/com/example/designpatterninkotlinjava/creational/factorymethod/FactoryMethodWithInterface.kt)
------------

is an interface or abstract class for creating an object but let the subclasses decide which class to instantiate. In other words, subclasses are responsible to create the instance of the class at runtime.

#### Example:

```kotlin
interface FlutterApp {
    fun generateAppFile(): String
}
class IOS : FlutterApp {
    override fun generateAppFile(): String = "ipk"
}
class AND : FlutterApp {
    override fun generateAppFile(): String = "apk"
}


/**
 * Base factory class. Note that "factory" is merely a role for the class. It
 * should have some core business logic which needs different products to be
 * created.
 */
abstract class MachineDeveloper {
    fun runTheApp(): Boolean {
        generateAppNow()
        // Ran the app already
        return true
    }
    abstract fun generateAppNow(): FlutterApp
}
class WindowOS : MachineDeveloper() {
    override fun generateAppNow(): FlutterApp {
        return AND()
    }
}
class MacOS : MachineDeveloper() {
    override fun generateAppNow(): FlutterApp {
        return IOS()
    }
}

/**
 * Factory Method
 */
fun generateAppByType(osType: String): MachineDeveloper {
    return if (osType == "AND") WindowOS() else MacOS()
}
```

#### Usage

```kotlin
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
```

[Abstract Factory](app/src/main/java/com/example/designpatterninkotlinjava/creational/factorymethod/FactoryMethodWithInterface.kt)
------------

for solves the problem of creating entire product families without specifying their concrete classes. The "family" of objects created by the factory are determined at run-time.

#### Component for create button:
```kotlin
interface Button {
    fun paint()
}
class MacOSButton : Button {
    override fun paint() {
        println("Mac os button!")
    }
}
class WindowOSButton : Button {
    override fun paint() {
        println("Window os button!")
    }
}
```

#### Component for create checkbox:
```kotlin
interface Checkbox {
    fun paint()
}
class MacOSCheckbox : Checkbox {
    override fun paint() {
        println("Mac os checkbox!")
    }
}
class WindowOSCheckbox : Checkbox {
    override fun paint() {
        println("Window os checkbox!")
    }
}
```

#### Factory for create specific button & checkbox:
```kotlin
interface MainFactory {
    fun createButton(): Button
    fun createCheckbox(): Checkbox
}

class MacButtonFactory : MainFactory {
    override fun createButton(): Button = MacOSButton()
    override fun createCheckbox(): Checkbox = MacOSCheckbox()
}

class WindowButtonFactory : MainFactory {
    override fun createButton(): Button = WindowOSButton()
    override fun createCheckbox(): Checkbox = WindowOSCheckbox()
}
```

#### Client code or library that provide client to use the component from factory
```kotlin
class Flutter(
    factory: MainFactory,
    private var checkbox: Checkbox = factory.createCheckbox(),
    var button: Button = factory.createButton()
) {
    fun paint() {
        checkbox.paint()
        button.paint()
    }
}
```

#### Usage
```kotlin
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

```

#### Output
```kotlin
window
Window os checkbox!
Window os button!
mac
Mac os checkbox!
Mac os button!
```
