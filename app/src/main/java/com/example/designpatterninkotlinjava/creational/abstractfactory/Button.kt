package com.example.designpatterninkotlinjava.creational.abstractfactory

// button families
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

// checkbox families
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

// Factory
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

// client code
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