package com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation

abstract class ActivationContract {

    protected var step = 1
    var nextStep: ActivationContract? = null

    fun doActivation(step: Int, msg: String) {
        if (this.step <= step) {
            performAction(msg)
        }
        nextStep?.doActivation(step, msg)
    }

    protected abstract fun performAction(msg: String?)

    companion object {
        var phoneNumberStep = 1
        var enterAccountStep = 2
        var loginStep = 3
    }
}


class PhoneNumberStep(var newStep: Int) : ActivationContract() {
    init {
        step = newStep
    }

    override fun performAction(msg: String?) {
        println("PhoneNumberStep : $step")
    }
}

class EnterAccountStep(var newStep: Int) : ActivationContract() {
    init {
        step = newStep
    }

    override fun performAction(msg: String?) {
        println("EnterAccountStep : $step")
    }
}

class LoginStep(var newStep: Int) : ActivationContract() {
    init {
        step = newStep
    }

    override fun performAction(msg: String?) {
        println("LoginStep : $step")
    }
}
