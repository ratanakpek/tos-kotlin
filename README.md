# Design Patterns In Kotlin & Java

Project maintained by [@ratanakpek](http://twitter.com/dbacinski) (Ratanak Pek)

Inspired by [@dbacinski](http://twitter.com/dbacinski) (Dariusz Baciński) & Phorn Borrom

## Table of Contents

* [Creational Patterns](#creational)
    * [Builder / Assembler](#builder--assembler)
    * [Factory Method](#factory-method)
    * [Singleton](#singleton)
    * [Abstract Factory](#abstract-factory)
    * [Prototype](#prototype)

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

Creational Pattern -> How you create object!
It provide various object creation mechanisms, which increase flexibility and reuse of existing
code. ==========

[Singleton](app/src/main/java/com/example/designpatterninkotlinjava/creational/singleton/java/Coin.kt)
------------

The singleton pattern ensures that only one object of a particular class is ever created. All
further references to objects of the singleton class refer to the same underlying instance. There
are very few applications, do not overuse this pattern!

1. More specifically, the singleton pattern allows objects to:

- Ensure they only have one instance
- Provide easy access to that instance
- Control their instantiation (for example, hiding the constructors of a class)
- Thread safe

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

is an interface or abstract class for creating an object but let the subclasses decide which class
to instantiate. In other words, subclasses are responsible to create the instance of the class at
runtime.

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

for solves the problem of creating entire product families without specifying their concrete
classes. The "family" of objects created by the factory are determined at run-time.

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

[Builder](app/src/main/java/com/example/designpatterninkotlinjava/creational/singleton/java/Coin.kt)
------------

to provide a flexible solution to various object creation problems in object-oriented programming.
The intent of the Builder design pattern is to separate the construction of a complex object from
its representation.

#### Example:

```kotlin
class SetupComputer private constructor(
    var ram: String?,
    var cpu: String?,
    var keyboard: String?
) {
    class Builder(
        var ram: String? = null,
        var cpu: String? = null,
        var keyboard: String? = null
    ) {
        fun addRam(ram: String) = apply { this.ram = ram }
        fun addCpu(cpu: String) = apply { this.cpu = cpu }
        fun addKeyboard(keyboard: String) = apply { this.keyboard = keyboard }
        fun build() = SetupComputer(ram, cpu, keyboard)
    }
}
```

#### Usage:

```kotlin
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
```

[Prototype](app/src/main/java/com/example/designpatterninkotlinjava/creational/prototype/OperatingSystem.kt)
------------

allow you to copy existing objects without making your code dependent on their classes.

#### Example:

```kotlin
interface OperatingSystemAndroid {
    fun clone(): OperatingSystemAndroid
}

class Samsung : OperatingSystemAndroid {
    var deviceId: String? = null
    var name: String? = null

    constructor()

    constructor(source: Samsung) {
        this.deviceId = source.deviceId
        this.name = source.name
    }

    override fun clone(): Samsung {
        return Samsung(this)
    }
}

class GooglePixel : OperatingSystemAndroid {
    var deviceId: String? = null
    var name: String? = null

    constructor()
    constructor(source: GooglePixel) {
        this.deviceId = source.deviceId
        this.name = source.name
    }

    override fun clone(): GooglePixel {
        return GooglePixel(this)
    }
}
```

#### Usage:

```kotlin
@Test
fun `Without use clone when we modify object, it will affect original test`() {
    val oldSamsung = Samsung()
    oldSamsung.apply {
        deviceId = "Samsung111"
        name = "Samsung Galaxy S22 Ultra 5g"
    }

    val newSamsung = oldSamsung
    newSamsung.apply {
        deviceId = "123"
        name = "haha"
    }

    //Both object had been modified, they have same value
    Assert.assertEquals(oldSamsung.deviceId, newSamsung.deviceId) //"123"
    Assert.assertEquals(oldSamsung.name, newSamsung.name) //"haha"
}

@Test
fun `Use clone() to copy the original object, and we can modify it as want test`() {
    val oldSamsung = Samsung()
    oldSamsung.apply {
        deviceId = "Samsung111"
        name = "Samsung Galaxy S22 Ultra 5g"
    }

    val newSamsung = oldSamsung.clone()
    newSamsung.apply {
        deviceId = "123"
        name = "haha"
    }

    //They are different object
    Assert.assertNotEquals(oldSamsung.deviceId, newSamsung.deviceId)
    Assert.assertNotEquals(oldSamsung.name, newSamsung.name)
}
```

Structural Design Patterns -> How you compose objects!
is a way of how classes and objects are structured together and used together to form larger
structures. There are 2 types of structural pattern: 1. Structural Class Pattern (Is-A) 2.
Structural Object Pattern (Has-A)

1. Structural Class Pattern (Is-A)
    - How classes are structured and interact
    - Focus on inheritance
    - It uses interface to share functionality
2. Structural Object Pattern (Has-A)
    - Object composition
    - Allow objects to change behavior at runtime ==========

[Adapter Pattern](app/src/main/java/com/example/designpatterninkotlinjava/creational/singleton/java/Coin.kt)
------------

It is a wrapper that allows incompatible objects to collaborate/connect each other.

#### Example:

```kotlin
/**
 * RoundPegs are compatible with RoundHoles but can not with square peg
 */
class RoundHole(var radius: Double) {
    fun fits(peg: RoundPeg): Boolean {
        return radius >= peg.radius
    }
}

/**
 * RoundPegs are compatible with RoundHoles.
 */
open class RoundPeg {
    open var radius: Double = 0.0
}

/**
 * SquarePegs are not fit with round hole, so we use adapter to help on this
 */
class SquarePeg(var width: Double) {
    fun getSquare() = this.width.pow(2.0)
}

/**
 * Adapter is a bridge that allow square peg to use fits() function of round hole
 */
class SquarePegAdapter(squarePeg: SquarePeg) : RoundPeg() {
    override var radius: Double = (sqrt((squarePeg.getSquare() / 2).pow(2.0) * 2))
}
```

#### Usage:

```kotlin
@Test
fun `Round hole fit with square peg success test`() {
    //My hole has radius of 10.0
    val hole = RoundHole(10.0)
    val roundPeg = RoundPeg()
    //formula find radius of Round Shape = 10.0
    roundPeg.radius = 10.0
    Assert.assertEquals(true, roundPeg.radius == hole.radius)
    Assert.assertEquals(true, hole.fits(roundPeg))
}

@Test
fun `Use adapter for square peg can not fit with round hole failed test`() {
    //My hole has radius of 10.0
    val hole = RoundHole(5.0)
    val squarePeg = SquarePeg(19.0)
    // if (hold.fits(squarePeg)) { // error bec it is round peg so we need adapter
    val sqPegAdapter = SquarePegAdapter(squarePeg)
    println("hole -> ${hole.radius}, square adapter -> ${sqPegAdapter.radius}") //hole -> 5.0, square adapter -> 255.26554800834364
    Assert.assertEquals(true, hole.radius != sqPegAdapter.radius)
    Assert.assertEquals(false, hole.fits(sqPegAdapter))
}

@Test
fun `Square peg smaller or fit with hole success test`() {
    val hole = RoundHole(5.0)
    val squarePeg2 = SquarePeg(2.0)
    val sqPegAdapter2 = SquarePegAdapter(squarePeg2)
    println("hole -> ${hole.radius}, square adapter -> ${sqPegAdapter2.radius}") // hole -> 5.0, square adapter -> 2.8284271247461903
    Assert.assertEquals(true, sqPegAdapter2.radius < hole.radius)
    Assert.assertEquals(
        true,
        hole.fits(sqPegAdapter2)
    ) //fit bec square peg is smaller than hole
}
```

[Decorator](app/src/main/java/com/example/designpatterninkotlinjava/structural/decorator/AndroidOSDecorator.kt)
------------
Act as a wrapper to existing or original class and provide additional functionality keeping class methods signature intact.

#### Example:

```kotlin
interface AndroidOS {
    fun runOsVersion()
}

class Nougat : AndroidOS {
    override fun runOsVersion() {
        println("Running on OS: Nougat!")
    }
}

class Oreo : AndroidOS {
    override fun runOsVersion() {
        println("Running on OS: Oreo!")
    }
}

abstract class OsDecorator(open var androidOS: AndroidOS) : AndroidOS {

    override fun runOsVersion() {
        androidOS.runOsVersion()
    }
}

//Add Color OS security on china's product like hauwei, oppo
class AddColorOSToOriginal(override var androidOS: AndroidOS) : OsDecorator(androidOS) {

    override fun runOsVersion() {
        androidOS.runOsVersion()
        addColorOSSecuritySystem()
    }

    fun addColorOSSecuritySystem() {
        println("This current OS embed with Color OS Security!")
    }
}
```

#### Usage:

```kotlin
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
```
