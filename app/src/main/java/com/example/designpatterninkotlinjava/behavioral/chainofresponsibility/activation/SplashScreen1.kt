package com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation

class SplashScreen1 : ActivationFlow() {
    override fun nextStep(user: User): String {
        println("Step: 1")
        return if (user.accountNumber.isEmpty() && user.phoneNumber.isEmpty()) {
            "Invalid user!"
        } else {
            activationFlow?.nextStep(user).orEmpty()
        }
    }
}