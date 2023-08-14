package com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation

class WelcomeScreen5 : ActivationFlow() {
    override fun nextStep(user: User): String {
        return "Congratulation!"
    }
}