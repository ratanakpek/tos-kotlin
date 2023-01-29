package com.example.designpatterninkotlinjava.creational.builder.kotlinstyle

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