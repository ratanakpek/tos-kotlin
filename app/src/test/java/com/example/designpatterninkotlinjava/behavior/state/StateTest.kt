package com.example.designpatterninkotlinjava.behavior.state

import com.example.designpatterninkotlinjava.behavioral.state.kotlin.MainActivity
import org.junit.Test

class StateTest {

    @Test
    fun state_pattern_test() {
        MainActivity().apply {
            //Create
            onCreate()
            println(this)
            println("resume: $isOnResume")
            println()//line break

            //Resume
            onResume()
            println(this)
            println("resume: $isOnResume")
            println()//line break

            //Destroy
            onDestroy()
            println(this)
            println("resume: $isOnResume")
            println()//line break
        }
    }
}