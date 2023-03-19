package com.example.delegate

import org.junit.Test

class VetoableDelegationTest {

    @Test
    fun `Observe that value changes base by condition test`() {
        val vetoableDelegation = VetoableDelegation()
        vetoableDelegation.myAge = 10
        println("State=" + vetoableDelegation.myAge) // State=18

        vetoableDelegation.myAge = 20
        println("State=" + vetoableDelegation.myAge) // State=20
    }
}