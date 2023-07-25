package com.example.designpatterninkotlinjava.behavior

import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation.ActivationContract
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation.EnterAccountStep
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation.LoginStep
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation.PhoneNumberStep
import org.junit.Test

class ChainOfResponsibilityTestKotlin {

    fun createChaining(): ActivationContract {
        val activationFlow1 = PhoneNumberStep(ActivationContract.phoneNumberStep)

        val activationFlow2 = EnterAccountStep(ActivationContract.enterAccountStep)
        activationFlow1.nextStep = activationFlow2

        val activationFlow3 = LoginStep(ActivationContract.loginStep)
        activationFlow2.nextStep = activationFlow3

        return activationFlow1;
    }

    @Test
    fun do_chain_responsibility_test() {
        val chaining = createChaining()
        chaining.doActivation(ActivationContract.loginStep, "Hello Login")
    }
}