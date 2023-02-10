package com.example.designpatterninkotlinjava.structural

import com.example.designpatterninkotlinjava.structural.AdapterSample.AdapterUkCarPriceAdapter
import com.example.designpatterninkotlinjava.structural.AdapterSample.CarPriceCalculator
import com.example.designpatterninkotlinjava.structural.AdapterSample.PriceCalculator
import com.example.designpatterninkotlinjava.structural.AdapterSample.TruckPriceCalculator
import com.example.designpatterninkotlinjava.structural.adaptercity.AsianAdapter
import com.example.designpatterninkotlinjava.structural.adaptercity.AsianCity
import com.example.designpatterninkotlinjava.structural.adaptercity.NorthAmericanCity
import com.example.designpatterninkotlinjava.structural.adaptercity.WeatherWarnings
import org.example.UKCarPriceCalculator
import org.junit.Assert
import org.junit.Test
import kotlin.math.pow
import kotlin.math.sqrt

class AdapterTest {
    @Test
    fun `Round hole fit with square peg success test`() {
        //My hole has radius of 10.0
        val hole = RoundHole(10.0)
        val roundPeg = RoundPeg()
        //formula find radius of Round Shape = 10.0
        roundPeg.radius = 10.0
        Assert.assertEquals(true, roundPeg.radius == hole.radius)
        Assert.assertEquals(true, hole.fits(roundPeg))
    }

    @Test
    fun `Use adapter for square peg can not fit with round hole failed test`() {
        //My hole has radius of 10.0
        val hole = RoundHole(5.0)
        val squarePeg = SquarePeg(19.0)
        // if (hold.fits(squarePeg)) { // error bec it is round peg so we need adapter
        val sqPegAdapter = SquarePegAdapter(squarePeg)
        println("hole -> ${hole.radius}, square adapter -> ${sqPegAdapter.radius}") //hole -> 5.0, square adapter -> 255.26554800834364
        Assert.assertEquals(true, hole.radius != sqPegAdapter.radius)
        Assert.assertEquals(false, hole.fits(sqPegAdapter))
    }

    @Test
    fun `Square peg smaller or fit with hole success test`() {
        val hole = RoundHole(5.0)
        val squarePeg2 = SquarePeg(2.0)
        val sqPegAdapter2 = SquarePegAdapter(squarePeg2)
        println("hole -> ${hole.radius}, square adapter -> ${sqPegAdapter2.radius}") // hole -> 5.0, square adapter -> 2.8284271247461903
        Assert.assertEquals(true, sqPegAdapter2.radius < hole.radius)
        Assert.assertEquals(
            true,
            hole.fits(sqPegAdapter2)
        ) //fit bec square peg is smaller than hole
    }

    @Test
    fun `Round hole is 5 and 2 is width of square so it will be failed for this test`() {
        val hole = RoundHole(5.0)
        val width = 2
        val squareRadius: Double = sqrt((width / 2.0).pow(2.0) * 2)
        println("hole -> ${hole.radius}, square -> $squareRadius") // hole -> 5.0, square -> 1.4142135623730951
        Assert.assertNotEquals(hole.radius, squareRadius, 0.0)
    }

    @Test
    fun `Round hole is 5 and width 20 of square so it will be failed for this test`() {
        val hole = RoundHole(5.0)
        val width = 20
        val squareRadius: Double = sqrt((width / 2.0).pow(2.0) * 2)
        println("hole radius -> $hole, square formula radius -> $squareRadius")
        Assert.assertEquals(true, squareRadius > hole.radius)
    }

    @Test
    fun `We can not access to jar file that has class UKPriceCaculator, we need to create adapter test`() {
        val carPriceCalculator = CarPriceCalculator("Ford", 3)
        printVehiclePrice(carPriceCalculator)
        val truckPriceCalculator = TruckPriceCalculator(10, 0)
        printVehiclePrice(truckPriceCalculator)

        //we call UKCarPriceCalculator from jar file
        val ukPriceCalculator = UKCarPriceCalculator("Audi", 2)
        val adapter = AdapterUkCarPriceAdapter(ukPriceCalculator)
        //UKCarPriceCalculator not support printVehiclePrice() so we use adapter instead
        printVehiclePrice(adapter)
    }

    private fun printVehiclePrice(calculator: PriceCalculator) {
        val price = calculator.calculatePrice()
        println("The price of vehicle is: $price")
    }


    @Test
    fun tet() {
        val weatherWarnings = WeatherWarnings()

        val chicago = NorthAmericanCity("Chicago", 16.0)
        weatherWarnings.postWarning(chicago)

        val phoenix = NorthAmericanCity("Phoenix", 104.0)
        weatherWarnings.postWarning(phoenix)

        val portland = NorthAmericanCity("Portland", 70.0)
        weatherWarnings.postWarning(portland)

        //50 degree is so hot
        val bangkok = AsianCity("Bangkok", 50.0)
        val adapter = AsianAdapter(bangkok)
        weatherWarnings.postWarning(adapter)
    }
}
