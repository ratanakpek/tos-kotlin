package com.example.delegate


import com.example.delegate.createdelegateproperty.Computer
import org.junit.Assert
import org.junit.Test

class CustomPropertyDelegationTest {
    @Test
    fun delegation_for_setter_getter_with_duplication_way_test() {
        val computer = Computer()
        //value must not null or empty and length 3 chars
        computer.id = ""
        Assert.assertEquals(null, computer.id)

        computer.id = "12"
        Assert.assertEquals(null, computer.id)

        computer.id = "123"
        Assert.assertEquals("123", computer.id)
    }

    @Test
    fun delegation_for_setter_getter_with_by_keyword_test() {
        val computer = Computer()
        //value must not null or empty and length 3 chars and using no duplicaiton
        computer.type = ""
        Assert.assertEquals(null, computer.id)

        computer.id = "12"
        Assert.assertEquals(null, computer.id)

        computer.id = "123"
        Assert.assertEquals("123", computer.id)
    }
}



