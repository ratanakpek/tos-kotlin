package com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation

class ChooseLanguage2 : ActivationFlow() {
    override fun nextStep(user: User): String {
        println("Step: 2")
        return if (user.language.isEmpty()) {
            "please select language!"
        } else {
            activationFlow?.nextStep(user).orEmpty()
        }
    }
}