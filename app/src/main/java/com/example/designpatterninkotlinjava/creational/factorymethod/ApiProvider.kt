package com.example.designpatterninkotlinjava.creational.factorymethod

abstract class ApiProvider {
    abstract fun loginWithDeviceType(): String
}

class IosPlatform : ApiProvider() {
    override fun loginWithDeviceType(): String = "Ios"
}

class AndroidPlatform : ApiProvider() {
    override fun loginWithDeviceType(): String = "Android"
}

class PcPlatform : ApiProvider() {
    override fun loginWithDeviceType(): String = "Pc"
}

class FactoryData {
    fun getPlatformByType(type: String): ApiProvider {
        return when (type) {
            "ios" -> {
                IosPlatform()
            }
            "and" -> AndroidPlatform()
            else -> {
                PcPlatform()
            }
        }

    }
}