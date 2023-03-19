package com.example.delegate

import org.junit.Test

class MessengerTest : DelegationMsg by DelegationMsg.DelegationImpl() {
    @Test
    fun delegation_test() {
        sendMsg("Hello World")
        deleteMsg()
    }
}



