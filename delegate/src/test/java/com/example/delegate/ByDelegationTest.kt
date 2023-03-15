package com.example.delegate

import org.junit.Test

class ByDelegationTest {

    @Test
    fun `Create student object and the println is not print any in console test`() {
        Student()
    }

    @Test
    fun `If we access the field, it will println test`() {
        Student().heavyOperation
    }

    @Test
    fun `Operation of creating the object just only one time test`() {
        Student().apply {
            //first call
            heavyOperation

            //second call
            heavyOperation
        }
    }
}