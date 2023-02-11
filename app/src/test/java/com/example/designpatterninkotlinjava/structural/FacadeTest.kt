package com.example.designpatterninkotlinjava.structural

import org.junit.Test

class FacadeTest {
    @Test
    fun `In order to allow Computer running, there are many steps and client might start wrong components test`() {
        CPU().run() //4. Running : CPU  -> client which one start first, it really complex to users
        SwitchPower().run() //1. Running : SwitchPower
        Monitor().run() //2. Running : Monitor
        Ram().run() //3. Running : Ram
    }

    @Test
    fun `We create facade ComputerFacade to solve these problem to clients test`() {
        val computerFacade = ComputerFacade()
        computerFacade.turnOnComputer()
    }
}
