package com.example.designpatterninkotlinjava.structural

interface ResetPersonalPhone {
    fun reset(password: String)
}

class RealOwner : ResetPersonalPhone {
    override fun reset(password: String) {
        println("Input this : $password to reset the phone!")
    }
}

//we can use class, or just methods in kotlin
fun secureLoginWithPassword(password: String): Boolean {
    //we are preventing password with easy access, you can apply another require info
    //can apply more validation like, notEmpty, length over 9, has letter, number, sign
    return if (password.isNotEmpty() && password.length > 9) {
        RealOwner().reset(password)
        println("Your phone has been reset successfully!")
        true
    } else {
        println("Your info is invalid, please try again!")
        false
    }
}
