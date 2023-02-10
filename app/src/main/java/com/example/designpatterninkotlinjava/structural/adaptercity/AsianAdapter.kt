package com.example.designpatterninkotlinjava.structural.adaptercity

class AsianAdapter(var asianCity: AsianCity) : City {

    override fun getName(): String {
        return asianCity.name
    }

    override fun getTemperature(): Double {
        return asianCity.temperature * 9 / 5 + 32
    }

    override fun getTemperatureScale(): String {
        return asianCity.temperatureScale
    }

    override fun getHasWeatherWarning(): Boolean {
        return asianCity.hasWeatherWarning
    }

    override fun setHasWeatherWarning(hasWeatherWarning: Boolean) {
        asianCity.hasWeatherWarning = hasWeatherWarning
    }
}