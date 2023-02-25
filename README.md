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

[Facade](app/src/main/java/com/example/designpatterninkotlinjava/structural/ComputerFacade.kt)
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

[Composite](app/src/main/java/com/example/designpatterninkotlinjava/structural/EmployeeComposite.kt)
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

[Protection Proxy](app/src/main/java/com/example/designpatterninkotlinjava/structural/ProtectionProxy.kt)
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

Behavioral design patterns ->  are concerned with algorithms and the assignment of responsibilities between objects.

[Command](app/src/main/java/com/example/designpatterninkotlinjava/behavioral/Command.kt)
------------
is wrapped under an object as command and passed to invoker object. 
Invoker object looks for the appropriate object which can handle this command and passes the command to the corresponding object which executes the command.

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

[Observer / Listener](app/src/main/java/com/example/designpatterninkotlinjava/behavioral/Observer.kt)
------------
The pattern provide a subscription mechanism that notifies multiple objects about any changes that happen to the observed object. Kotlin has built-in like observable, vetoable. 
If we write this pattern, we will end up a lot of line code, but with the help of Kotlin, it's simple and short.

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
Old= <Initialize value>, New= First Text 
Old= First Text, New= Second Text
```

[State](app/src/main/java/com/example/designpatterninkotlinjava/behavioral/kotlin/ActivityLifeCycle.kt)
------------
It can create objects which represent various states and a context object whose behavior varies as its state object changes.


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