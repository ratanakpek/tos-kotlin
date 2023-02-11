package com.example.designpatterninkotlinjava.structural.decorator

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

