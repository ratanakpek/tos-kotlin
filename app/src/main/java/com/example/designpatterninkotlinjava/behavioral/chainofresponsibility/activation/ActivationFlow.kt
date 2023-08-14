package com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation

abstract class ActivationFlow {
    @JvmField
    protected var activationFlow: ActivationFlow? = null

    fun setActivationFlow(activationFlow: ActivationFlow) {
        this.activationFlow = activationFlow
    }

    abstract fun nextStep(user: User): String
}