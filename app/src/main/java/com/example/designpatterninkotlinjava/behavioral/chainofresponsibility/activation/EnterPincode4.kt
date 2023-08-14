package com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation

class EnterPincode4 : ActivationFlow() {
    override fun nextStep(user: User): String {
        println("Step: 4")
        return if (user.pin.isEmpty()) {
            "Please enter your pin!"
        } else {
            activationFlow?.nextStep(user).orEmpty()
        }
    }
}