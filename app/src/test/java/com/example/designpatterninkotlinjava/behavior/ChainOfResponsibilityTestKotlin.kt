package com.example.designpatterninkotlinjava.behavior

import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation.ChooseLanguage2
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation.EnterAccountNumber3
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation.EnterPincode4
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation.SplashScreen1
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation.User
import com.example.designpatterninkotlinjava.behavioral.chainofresponsibility.activation.WelcomeScreen5
import org.junit.Test


class ChainOfResponsibilityTestKotlin {

    @Test
    fun `Activation on app test`() {
        //Application step
        val step1 = SplashScreen1()
        val step2 = ChooseLanguage2()
        val step3 = EnterAccountNumber3()
        val step4 = EnterPincode4()
        val finalStep = WelcomeScreen5()

        //chain
        step1.setActivationFlow(step2)
        step2.setActivationFlow(step3)
        step3.setActivationFlow(step4)
        step4.setActivationFlow(finalStep)

        //if user not enter valid information,
        //so user will not able to reach final step
        val user = User()
        user.accountNumber = "010555213"
        user.language = "EN"
        user.pin = "1234"

        println(step1.nextStep(user))
    }
}