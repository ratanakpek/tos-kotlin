package com.example.designpatterninkotlinjava.creational.factorymethod

// # Use interface
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