package com.example.designpatterninkotlinjava.structural.AdapterSample

import org.example.UKCarPriceCalculator
import kotlin.math.max

interface PriceCalculator {
    fun calculatePrice(): String?
}

class AdapterUkCarPriceAdapter(private var ukCarPriceCalculator: UKCarPriceCalculator) : PriceCalculator{
    override fun calculatePrice(): String? {
       return ukCarPriceCalculator.price.toString().plus("GBP")
    }

}

class TruckPriceCalculator(private val age: Int, private val mileage: Int) : PriceCalculator {
    override fun calculatePrice(): String? {
        val price = max(averagePrice - age * 100 - mileage / 100, 0)
        return price.toString() + "USD"
    }

    companion object {
        var averagePrice = 10000
    }
}

class CarPriceCalculator(private val model: String, private val age: Int) : PriceCalculator {
    private val retailPrice: Int
        get() = when (model) {
            "Ford" -> 3000
            "Audi" -> 5000
            "BMW" -> 7000
            "Tesla" -> 10000
            else -> averageCarPrice
        }

    override fun calculatePrice(): String? {
        val price = max(retailPrice - age * 100, 0)
        return price.toString() + "USD"
    }

    companion object {
        var averageCarPrice = 6000
    }
}

