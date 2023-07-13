# Design Patterns In Kotlin & Java

Project maintained by [@ratanakpek](http://twitter.com/dbacinski) (Ratanak Pek)

Inspired by [@dbacinski](http://twitter.com/dbacinski) (Dariusz Baciński) & Phorn Borrom

## Table of Contents

* [Creational Patterns](#creational)
  1. [Singleton](#singleton)
  2. [Factory Method](#factory-method)
  3. [Abstract Factory](#abstract-factory)
  4. [Builder / Assembler](#builder--assembler)
  5. [Prototype](#prototype)

* [Structural Patterns](#structural)
  1. [Adapter](#adapter)
  2. [Decorator](#decorator)
  3. [Facade](#facade)
  4. [Composite](#composite)
  5. [Proxy Pattern](#proxy)
  6. [Flyweight Pattern](#flyweight)
  7. [Protection Proxy](#protection-proxy)

* [Behavioral Patterns](#behavioral)
  1. [Command](#command)
  2. [Observer / Listener](#observer--listener)
  3. [State](#state)
  4. [Mediator](#mediator)
  5. [Memento pattern](#memento)
  6. [Iterator](#iterator)
  7. [Visitor](#visitor)
  8. [Strategy](#strategy)
  9. [Chain of Responsibility](#chain-of-responsibility)


//////////////// DESIGN PATTERN //////////////////

Creation Pattern:
Define how we create object! It provide various object creation mechanisms, which increase
flexibility and reuse of existing code.

1. [Singleton](app/src/main/java/com/example/designpatterninkotlinjava/creational/singleton/java/Coin.kt)
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
            if (instance == null) instance = LazySingleton()
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

2. [Factory Method](app/src/main/java/com/example/designpatterninkotlinjava/creational/factorymethod/FactoryMethodWithInterface.kt)
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

3. [Abstract Factory](app/src/main/java/com/example/designpatterninkotlinjava/creational/factorymethod/FactoryMethodWithInterface.kt)
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

4. [Builder / Assembler](app/src/main/java/com/example/designpatterninkotlinjava/creational/builder/kotlinstyle/SetupComputer.kt)
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

5. [Prototype](app/src/main/java/com/example/designpatterninkotlinjava/creational/prototype/OperatingSystem.kt)
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

Structural Design Patterns ->
Define how we compose objects, is a way of how classes and objects are structured together and used
together to form larger structures. There are 2 types of structural pattern:

1. Structural Class Pattern (Is-A)
    - How classes are structured and interact
    - Focus on inheritance
    - It uses interface to share functionality
2. Structural Object Pattern (Has-A)
    - Object composition
    - Allow objects to change behavior at runtime ==========

6. [Adapter](app/src/main/java/com/example/designpatterninkotlinjava/structural/Adapter.kt)
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

7. [Decorator](app/src/main/java/com/example/designpatterninkotlinjava/structural/decorator/AndroidOSDecorator.kt)
------------
Act as a wrapper to existing or original class and provide additional functionality keeping class
methods signature intact.

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

#### Output

```kotlin
Nougat with original from Google
Running on OS: Nougat!

Nougat with original from Google with color OS System!
Running on OS: Nougat!
This current OS embed with Color OS Security !

Oreo with original from Google with color OS System!
Running on OS: Oreo!
This current OS embed with Color OS Security !
```

8. [Facade](app/src/main/java/com/example/designpatterninkotlinjava/structural/ComputerFacade.kt)
------------
It hides the complexities of the system and provides an interface to the client using which the
client can access the system.

#### Example:

```kotlin
interface ComputerRunnable {
    fun run()
}

class SwitchPower : ComputerRunnable {
    override fun run() {
        println("1. Running : SwitchPower")
    }
}

class Monitor : ComputerRunnable {
    override fun run() {
        println("2. Running : Monitor")
    }
}

class Ram : ComputerRunnable {
    override fun run() {
        println("3. Running : Ram")
    }
}

class CPU : ComputerRunnable {
    override fun run() {
        println("4. Running : CPU")
    }
}

class ComputerFacade {
    fun turnComputer() {
        SwitchPower().run()
        Monitor().run()
        Ram().run()
        CPU().run()
    }
}
```

#### Usage:

```kotlin
 @Test
fun `In order to allow Computer running, there are many steps and client might start wrong components test`() {
    CPU().run() //4. Running : CPU  -> client which one start first, it really complex to users
    SwitchPower().run() //1. Running : SwitchPower
    Monitor().run() //2. Running : Monitor
    Ram().run() //3. Running : Ram
}

@Test
fun `Client doesn't need to know all components, just call turnOnComputer() method test`() {
    val computerFacade = ComputerFacade()
    computerFacade.turnOnComputer()
}
```

#### Output

```kotlin
4.Running : CPU
1.Running : SwitchPower
2.Running : Monitor
3.Running : Ram

//Using facade, clients no need to understand which component to start first

1.Running : SwitchPower
2.Running : Monitor
3.Running : Ram
4.Running : CPU
```

9. [Composite](app/src/main/java/com/example/designpatterninkotlinjava/structural/EmployeeComposite.kt)
------------
is to treat a group of objects or single object in the same way in term of a tree structure to
represent part as well as whole hierarchy.

#### Example:

```kotlin
class Employee(
    var name: String,
    var dept: String,
    var salary: Int
) {
    var subordinates = mutableListOf<Employee>()

    //for demo purpose
    override fun toString(): String {
        return "Employee :[ Name : $name, dept : $dept, salary :$salary ]"
    }
}
```

#### Usage:

```kotlin
@Test
fun `Saving & Investing organization test`() {
    //management team
    val ceo = Employee("Ratanak", "CEO", 5000)

    //Android Developer team
    val headOfAndroid = Employee("Makara", "Head of Android", 2000)
    ceo.subordinates.apply {
        add(headOfAndroid)
    }
    val andDev1 = Employee("Bora", "Android Developer", 1000)
    val andDev2 = Employee("Tula", "Android Developer", 1000)
    val andDev3 = Employee("Kanha", "Android Developer", 1000)
    headOfAndroid.subordinates.apply {
        add(andDev1)
        add(andDev2)
        add(andDev3)
    }

    // IOS Developer team
    val headOfIOS = Employee("Chantha", "Head of IOS", 2000)
    ceo.subordinates.apply {
        add(headOfIOS)
    }
    val iosDev1 = Employee("Dara", "IOS Developer", 1000)
    val iosDev2 = Employee("Channa", "IOS Developer", 1000)
    val iosDev3 = Employee("Kdey", "IOS Developer", 1000)
    headOfIOS.subordinates.apply {
        add(iosDev1)
        add(iosDev2)
        add(iosDev3)
    }

    println("$ceo of Saving & Investing Organization")
    for (head in ceo.subordinates) {
        println(head)
        for (employee in head.subordinates) {
            println(employee)
        }
    }
}
```

#### Output

```kotlin
Employee :[Name : Ratanak, dept : CEO, salary :5000 ] of Saving & Investing Organization
Employee :[Name : Makara, dept : Head of Android, salary :2000 ]
Employee :[Name : Bora, dept : Android Developer, salary :1000 ]
Employee :[Name : Tula, dept : Android Developer, salary :1000 ]
Employee :[Name : Kanha, dept : Android Developer, salary :1000 ]
Employee :[Name : Chantha, dept : Head of IOS, salary :2000 ]
Employee :[Name : Dara, dept : IOS Developer, salary :1000 ]
Employee :[Name : Channa, dept : IOS Developer, salary :1000 ]
Employee :[Name : Kdey, dept : IOS Developer, salary :1000 ]
```

10. [Proxy Pattern](app/src/main/java/com/example/designpatterninkotlinjava/structural/ProxyPattern.kt)
------------
It means an object representing another object, and provide the control for accessing the original
object. Example: when we perform operations like validating the object, hiding the information of
original object, on demand loading etc.

#### Example:

```kotlin
class ProxyImage(var fileName: String) : Image {
    private var realImage: RealImage? = null

    override fun display() {
        if (realImage == null) {
            realImage = RealImage(fileName)
        }
        println()
        println("Cache from proxy")
        realImage?.display()
    }
}
```

#### Usage:

```kotlin
@Test
fun `Demo of proxy pattern test`() {
    val image = ProxyImage("my_vdo.mp4")
    //image will load from original of the disk
    image.display()

    //image will load form proxy instead
    image.display()
}
```

#### Output

```kotlin
Loading my_vdo . mp4

        Cache from proxy
Displaying my_vdo . mp4

        Cache from proxy
Displaying my_vdo . mp4
```

11. [Flyweight Pattern](app/src/main/java/com/example/designpatterninkotlinjava/structural/FlyweightPattern.kt)
------------

- To lower the number of objects generated, decrease the memory footprint, and improve performance.
- It offers strategies for reducing the number of objects while enhancing the application's object
  structure.
- When no existing object of the same kind can be identified, the flyweight pattern seeks to reuse
  previously created objects of a similar sort by storing them.
#### Example:

```kotlin
class ShapeFactory {
    var circleMap = HashMap<String, Shape>()

    // It will check, is black circle already exist?
    // If already exist just use it
    fun getCircle(color: String): Shape {
        var circle = circleMap[color]
        return if (circle == null) {
            circle = Circle(getRandomXY(), getRandomXY(), color)
            circleMap[color] = circle
            circle
        } else circle
    }

    private fun getRandomXY() = (Math.random() * 100).toInt()
}
```

#### Usage:

```kotlin
 @Test
fun `flyweight design test`() {
    val shapeFactory = ShapeFactory()
    colors.forEach {
        val circle = shapeFactory.getCircle(it)
        circle.draw()
    }
}
```

#### Output

```kotlin
Color: White, X : 20, Y : 73
Color: Black, X : 38, Y : 52
Color: Black, X : 38, Y : 52
```


12. [Protection Proxy](app/src/main/java/com/example/designpatterninkotlinjava/structural/ProtectionProxy.kt)
------------
is a class represents functionality of another class by require more information than original
class. It can be a protection layer to original class or data.

#### Example:

```kotlin
interface ResetPersonalPhone {
    fun reset(password: String)
}

class RealOwner : ResetPersonalPhone {
    override fun reset(password: String) {
        println("Input this : $password to reset the phone!")
    }
}

fun secureLoginWithPassword(password: String): Boolean {
    //we are preventing password with easy access, you can apply another require info
    //can apply more validation like, notEmpty, length over 9, has letter, number, sign
    return if (password.isNotEmpty() && password.length > 9) {
        RealOwner().reset(password)
        println("Your phone has been reset successfully!")
        true
    } else {
        println("Your info is invalid, please try again!")
        false
    }
}
```

#### Usage:

```kotlin
@Test
fun `Provide more complex validation for login into the system test`() {
    println("Invalid Password!")
    Assert.assertEquals(false, secureLoginWithPassword(""))
    Assert.assertEquals(false, secureLoginWithPassword("hello"))
    println()
    println("Valid Password with proxy requirement!")
    Assert.assertEquals(true, secureLoginWithPassword("helloWorld"))
    println()
    Assert.assertEquals(true, secureLoginWithPassword("hello12345"))
}
```

#### Output

```kotlin
Invalid Password !
Your info is invalid, please try again!
    Your info is invalid, please try again!

    Valid Password with proxy requirement!
    Input this : helloWorld to reset the phone!
    Your phone has been reset successfully !

    Input this : hello12345 to reset the phone!
    Your phone has been reset successfully !
```

Behavioral design patterns:
are concerned with algorithms and the assignment of responsibilities between objects.

13. [Command](app/src/main/java/com/example/designpatterninkotlinjava/behavioral/Command.kt)
------------
is wrapped under an object as command and passed to invoker object. Invoker object looks for the
appropriate object which can handle this command and passes the command to the corresponding object
which executes the command.

#### Example:

```kotlin
interface MovieTicketOrder {
    fun orderNow()
}

class LegendCinema {
    fun avengerMovie() {
        println("Movie : Avenger Ticket has been ordered!")
    }

    fun hunterGhost() {
        println("Movie : HunterGhost Ticket has been ordered!")
    }
}

class WatchAvengerCommand(var cinema: LegendCinema) : MovieTicketOrder {
    override fun orderNow() {
        cinema.avengerMovie()
    }
}

class WatchHunterGhostCommand(var cinema: LegendCinema) : MovieTicketOrder {
    override fun orderNow() {
        cinema.hunterGhost()
    }
}

class TicketOrderByABA {
    private var bookTickets = mutableListOf<MovieTicketOrder>()

    fun bookAndPaid(orderTicket: MovieTicketOrder) {
        bookTickets.add(orderTicket)
    }

    fun buyTicketForClient() {
        //send order info to merchant
        bookTickets.forEach { it.orderNow() }

        //clear orders
        bookTickets.clear()
    }
}
```

#### Usage:

```kotlin
 @Test
fun `Command pattern success test`() {
    val movies = LegendCinema()
    val orderAvengerTicket = WatchAvengerCommand(movies)
    val orderHunterGhostTicket = WatchHunterGhostCommand(movies)

    val ticketByABA = TicketOrderByABA()
    ticketByABA.bookAndPaid(orderAvengerTicket)
    ticketByABA.bookAndPaid(orderHunterGhostTicket)
    ticketByABA.buyTicketForClient()
}
```

#### Output

```kotlin
Movie : Avenger Ticket has been ordered!
Movie : HunterGhost Ticket has been ordered!
```

14. [Observer / Listener](app/src/main/java/com/example/designpatterninkotlinjava/behavioral/Observer.kt)
------------
The pattern provide a subscription mechanism that notifies multiple objects about any changes that
happen to the observed object. Kotlin has built-in like observable, vetoable. If we write this
pattern, we will end up a lot of line code, but with the help of Kotlin, it's simple and short.

#### Example:

```kotlin
class ObservableInKotlin {
    //Kotlin able to observable another type like boolean, double, float, ...
    var text: String by Delegates.observable("<Initialize value>") { _, oldValue, newValue ->
        println("Old= $oldValue, New= $newValue")
    }
}
```

#### Usage:

```kotlin
@Test
fun observer_test() {
    val stringObservable = ObservableInKotlin()
    with(stringObservable) {
        //1
        text = "First Text"
        //2
        text = "Second Text"
    }
    Assert.assertEquals(true, stringObservable.text == "Second Text")
}
```

#### Output

```kotlin
Old = < Initialize value >, New = First Text
        Old = First Text, New = Second Text
```

15. [State](app/src/main/java/com/example/designpatterninkotlinjava/behavioral/kotlin/ActivityLifeCycle.kt)
------------
It can create objects which represent various states and a context object whose behavior varies as
its state object changes.

#### Example:

```kotlin
sealed class ActivityLifeCycle {
    object OnCreate : ActivityLifeCycle()

    object OnResume : ActivityLifeCycle()

    object OnDestroy : ActivityLifeCycle()
}

class MainActivity(private var state: ActivityLifeCycle = ActivityLifeCycle.OnCreate) {

    val isOnResume: Boolean
        get() = when (state) {
            is ActivityLifeCycle.OnResume -> true
            else -> false
        }

    fun onCreate() {
        state = ActivityLifeCycle.OnCreate
    }

    fun onResume() {
        state = ActivityLifeCycle.OnResume
    }

    fun onDestroy() {
        state = ActivityLifeCycle.OnDestroy
    }

    override fun toString() = "State : ${
        when (state) {
            is ActivityLifeCycle.OnCreate -> "Create"
            is ActivityLifeCycle.OnResume -> "Resume"
            is ActivityLifeCycle.OnDestroy -> "Destory"
        }
    }"

}
```

#### Usage:

```kotlin
@Test
fun state_pattern_test() {
    MainActivity().apply {
        //Create
        onCreate()
        println(this)
        println("resume: $isOnResume")
        println()//line break

        //Resume
        onResume()
        println(this)
        println("resume: $isOnResume")
        println()//line break

        //Destroy
        onDestroy()
        println(this)
        println("resume: $isOnResume")
        println()//line break
    }
}
```

#### Output

```kotlin
State : Create
resume: false

State : Resume
resume: true

State : Destory
resume: false
```

16. [Mediator](app/src/main/java/com/example/designpatterninkotlinjava/behavioral/mediator/Mediator.kt)
------------
It helps us to design the system in such a way that components are loosely coupled and reusable.

#### Example:

```kotlin
class PowerButton {
    fun powerOn() {
        println("Power on")
    }
}

class MonitorDisplay {
    fun monitorDisplay() {
        println("Display monitor")
    }
}

class MediatorPhone(
    var powerButton: PowerButton,
    var monitorShower: MonitorDisplay
) {
    fun powerOnPhone() {
        powerButton.powerOn()
        monitorShower.monitorDisplay()
    }
}
```

#### Usage:

```kotlin
@Test
@Test
fun `If we don't use MediatorPhone class, the Phone & monitor will couple to each other`() {
    MediatorPhone(
        PowerButton(),
        MonitorDisplay()
    ).powerOnPhone()
}
```

#### Output

```kotlin
Power on
        Display monitor
```

17. [Memento pattern](app/src/main/java/com/example/designpatterninkotlinjava/behavioral/memento/Memento.kt)
------------
is used to restore state of an object to a previous state.

#### Example

```kotlin
data class Memento(val state: String)

class Originator(var state: String) {
    fun createMemento() = Memento(state)
    fun restore(memento: Memento) {
        state = memento.state
    }
}

class TextEditor {
    private val mementoList = mutableListOf<Memento>()
    fun saveState(state: Memento) {
        mementoList.add(state)
    }

    fun restore(index: Int) = mementoList[index]
}
```

#### Usage

```kotlin
 @Test
fun memento() {
    val originator = Originator("initial state")
    val textEditor = TextEditor()
    textEditor.saveState(originator.createMemento())

    originator.state = "State #1"
    originator.state = "State #2"
    textEditor.saveState(originator.createMemento())

    originator.state = "State #3"
    println("Current State: " + originator.state)
    Assert.assertEquals(true, originator.state == "State #3")

    originator.restore(textEditor.restore(1))
    println("Second saved state: " + originator.state)
    Assert.assertEquals(true, originator.state == "State #2")

    originator.restore(textEditor.restore(0))
    println("First saved state: " + originator.state)
    Assert.assertEquals(true, originator.state == "initial state")
}
```

#### Output

```kotlin
Current State : State #3
Second saved state: State #2
First saved state: initial state
```

18. [Iterator pattern](app/src/main/java/com/example/designpatterninkotlinjava/behavioral/iterator/sample/StockIterator.kt)
------------
Allow us to traverse the collection without worrying the structure of collection (list, stack, tree,
etc.)

#### Example:

```kotlin
public class StockIterator implements Iterator {
    private int index;
    private Inventory inventory;

    public StockIterator(Inventory inventory) {
        this.inventory = inventory;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        Item[] items = inventory.getItems();
        if (index < items.length) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Item next() {
        Item[] items = inventory.getItems();
        if (hasNext()) {
            Item item = items[index++];
            if (item.getQuantity() > 0) {
                return item;
            } else {
                return next();
            }
        } else {
            return null;
        }
    }
}
```

#### Usage:

```kotlin
 @Test
fun iterator_test() {
    val employeeList = listOf(
        Employee("Dara Chan", 20),
        Employee("Makara Pich", 14),
        Employee("Pov Ponlok", 26)
    )

    val adminInfo = AdminInfo(employeeList.toMutableList())
    val itDepartment = adminInfo.iterator()
    while (itDepartment.hasNext()) {
        val item = itDepartment.next()
        println(item.name)
    }
}
```

#### Output

```kotlin
Dara Chan 
Pov Ponlok
```

19. [Visitor](app/src/main/java/com/example/designpatterninkotlinjava/behavioral/visitor.kt)
------------
when we need to add similar extraneous functionality to many different classes.

#### Example:

```kotlin
interface FetchData {
    fun accept(computerPartVisitor: FetchDataPartVisitor)
}

interface FetchDataPartVisitor {
    fun visit(sql: Sql)
    fun visit(json: Json)
    fun visit(text: TextFile)
}

class Json : FetchData {
    override fun accept(fetchDataPartVisitor: FetchDataPartVisitor) {
        fetchDataPartVisitor.visit(this)
    }
}

class TextFile : FetchData {
    override fun accept(fetchDataPartVisitor: FetchDataPartVisitor) {
        fetchDataPartVisitor.visit(this)
    }
}

class Sql : FetchData {
    override fun accept(computerPartVisitor: FetchDataPartVisitor) {
        computerPartVisitor.visit(this)
    }
}

class FetchDataPartDisplayVisitor : FetchDataPartVisitor {
    override fun visit(sql: Sql) {
        println("Store data with SQL, as existing in our project!")
    }

    override fun visit(json: Json) {
        println("Store data as json!")
    }

    override fun visit(text: TextFile) {
        println("Store data in text file!")
    }
}
```

#### Usage:

```kotlin
@Test
fun visitor_test_demo() {
    val displayVisitor = FetchDataPartDisplayVisitor()
    //This is old way to store data in sql
    Sql().apply {
        accept(displayVisitor)
    }

    //new way to store data as json
    Json().apply {
        accept(displayVisitor)
    }

    //new way to store data text file instead
    TextFile().apply {
        accept(displayVisitor)
    }
}
```

#### Output

```kotlin
Store data with SQL, as existing in our project !
Store data as json !
Store data in text file!
```
20. [Strategy](app/src/main/java/com/example/designpatterninkotlinjava/behavioral/visitor.kt)
------------
is about algorithm can be changed at run time.

#### Example:

```kotlin
interface Strategy {
  fun doOperation(num1: Int, num2: Int): Int
}

class OperationAdd : Strategy {
  override fun doOperation(num1: Int, num2: Int): Int {
    return num1 + num2
  }
}
```

#### Usage:

```kotlin
 @Test
fun `Creating strategy operation test`() {
  val contextOperationAdd = Context(OperationAdd())
  println("5 + 5 = ${contextOperationAdd.executeStrategy(5, 5)}")

  val contextOperationSubtract = Context(OperationSubtract())
  println("5 - 5 = ${contextOperationSubtract.executeStrategy(5, 5)}")

  val contextOperationMultiply = Context(OperationMultiply())
  println("5 * 5 = ${contextOperationMultiply.executeStrategy(5, 5)}")
}
```

#### Output
```kotlin
5 + 5 = 10
5 - 5 = 0
5 * 5 = 25
```