package com.example.designpatterninkotlinjava.structural

import org.junit.Assert
import org.junit.Test

class ProtectionProxyTest {
    @Test
    fun `Provide more complex validation for login into the system test`() {
        println("Invalid Password!")
        Assert.assertEquals(false, secureLoginWithPassword(""))
        Assert.assertEquals(false, secureLoginWithPassword("hello"))
        println()
        println("Valid Password with proxy requirement!")
        Assert.assertEquals(true, secureLoginWithPassword("helloWorld"))
        println()
        Assert.assertEquals(true, secureLoginWithPassword("hello12345"))
    }
}
