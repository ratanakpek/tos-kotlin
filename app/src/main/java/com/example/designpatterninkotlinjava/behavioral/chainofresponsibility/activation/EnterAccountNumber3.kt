package com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation

class EnterAccountNumber3 : ActivationFlow() {
    override fun nextStep(user: User): String {
        println("Step: 3")
        return if (user.accountNumber.isEmpty()) {
            "Please enter your account number!"
        } else {
            activationFlow?.nextStep(user).orEmpty()
        }
    }
}