package com.example.designpatterninkotlinjava.structural

interface Window {
    fun build()
}

class XWindow : Window {
    override fun build() {
        println("x window")
    }
}

class PMWindow : Window {
    override fun build() {
        println("pm window")
    }
}


open class IconWindow : Window {
    override fun build() {
        println("icon window")
    }
}

class XIconWindow : IconWindow() {
    override fun build() {
        println("x icon window")
    }
}

class PMIconWindow : IconWindow() {
    override fun build() {
        println("pm icon window")
    }
}

