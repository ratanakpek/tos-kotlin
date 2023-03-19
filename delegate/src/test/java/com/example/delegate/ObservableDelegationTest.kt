package com.example.delegate

import org.junit.Test

class ObservableDelegationTest {

    @Test
    fun `Demo observable function from Kotlin test`() {
        val observableDelegation = ObservableDelegation()
        observableDelegation.myName = "Hello World" //Old=Kid, new=Hello World

        observableDelegation.myName = "Saving & Investing" //Old=Hello World, new=Saving & Investing
    }
}