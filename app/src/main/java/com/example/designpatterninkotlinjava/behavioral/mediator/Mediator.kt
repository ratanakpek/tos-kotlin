package com.example.designpatterninkotlinjava.behavioral.mediator.java

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