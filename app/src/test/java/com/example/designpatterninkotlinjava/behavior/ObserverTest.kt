package com.example.designpatterninkotlinjava.behavior

import com.example.designpatterninkotlinjava.behavioral.ObservableInKotlin

import org.junit.Assert
import org.junit.Test

class ObserverTest {

    @Test
    fun observer_test() {
        val stringObservable = ObservableInKotlin()
        with(stringObservable) {
            //1
            text = "First Text"
            //2
            text = "Second Text"
        }
        Assert.assertEquals(true, stringObservable.text == "Second Text")
    }
}

