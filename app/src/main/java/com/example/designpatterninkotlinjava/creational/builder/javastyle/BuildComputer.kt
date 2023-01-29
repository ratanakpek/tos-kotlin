package com.example.designpatterninkotlinjava.creational.builder.javastyle

class BuildComputer private constructor(builder: BuildComputer.Builder) {
    val ram: String?
    val cpu: String?
    val keyboard: String?
    val mouse: String?

    init {
        this.ram = builder.ram
        this.cpu = builder.cpu
        this.keyboard = builder.keyboard
        this.mouse = builder.mouse
    }

    class Builder {
        var ram: String? = null
            private set

        var cpu: String? = null
            private set

        var keyboard: String? = null
            private set

        var mouse: String? = null
            private set

        fun addRam(ram: String) = apply { this.ram = ram }
        fun addCpu(cpu: String) = apply { this.cpu = cpu }
        fun addKeyboard(keyboard: String) = apply { this.keyboard = keyboard }
        fun addMouse(mouse: String) = apply { this.mouse = mouse }
        fun build() = BuildComputer(this)
    }
}