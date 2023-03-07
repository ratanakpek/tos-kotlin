package com.example.delegate

interface DelegationMsg {
    fun sendMsg(msg: String)

    fun deleteMsg()

    class DelegationImpl : DelegationMsg {
        override fun sendMsg(msg: String) {
            println(msg)
        }

        override fun deleteMsg() {
            println("Delete!")
        }
    }
}




