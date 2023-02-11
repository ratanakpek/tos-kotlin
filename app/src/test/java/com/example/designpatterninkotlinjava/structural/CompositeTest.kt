package com.example.designpatterninkotlinjava.structural

import org.junit.Test

class CompositeTest {
    @Test
    fun `Saving & Investing organization test`() {
        //management team
        val ceo = Employee("Ratanak", "CEO", 5000)

        //Android Developer team
        val headOfAndroid = Employee("Makara", "Head of Android", 2000)
        ceo.subordinates.apply {
            add(headOfAndroid)
        }
        val andDev1 = Employee("Bora", "Android Developer", 1000)
        val andDev2 = Employee("Tula", "Android Developer", 1000)
        val andDev3 = Employee("Kanha", "Android Developer", 1000)
        headOfAndroid.subordinates.apply {
            add(andDev1)
            add(andDev2)
            add(andDev3)
        }

        // IOS Developer team
        val headOfIOS = Employee("Chantha", "Head of IOS", 2000)
        ceo.subordinates.apply {
            add(headOfIOS)
        }
        val iosDev1 = Employee("Dara", "IOS Developer", 1000)
        val iosDev2 = Employee("Channa", "IOS Developer", 1000)
        val iosDev3 = Employee("Kdey", "IOS Developer", 1000)
        headOfIOS.subordinates.apply {
            add(iosDev1)
            add(iosDev2)
            add(iosDev3)
        }

        println("$ceo of Saving & Investing Organization")
        for (head in ceo.subordinates) {
            println(head)
            for (employee in head.subordinates) {
                println(employee)
            }
        }
    }
}
