package com.example.designpatterninkotlinjava.structural

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
    fun turnOnComputer() {
        SwitchPower().run()
        Monitor().run()
        Ram().run()
        CPU().run()
    }
}


