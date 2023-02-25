package com.example.designpatterninkotlinjava.behavioral.mediator.java

class PowerButton1 {
    fun powerOn() {
        println("Power on")
        //couple to other class
        MonitorDisplay().monitorDisplay()
        Ram1().ramProcess()
    }
}

class Ram1 {
    fun ramProcess() {
        println("Ram processing")
    }
}

class MonitorDisplay1 {
    fun monitorDisplay() {
        println("Display monitor")
    }
}