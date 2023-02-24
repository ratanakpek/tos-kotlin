package com.example.designpatterninkotlinjava.behavior


import com.example.designpatterninkotlinjava.behavioral.LegendCinema
import com.example.designpatterninkotlinjava.behavioral.TicketOrderByABA
import com.example.designpatterninkotlinjava.behavioral.WatchAvengerCommand
import com.example.designpatterninkotlinjava.behavioral.WatchHunterGhostCommand
import org.junit.Test

class CommandTest {

    @Test
    fun `Command pattern success test`() {
        val movies = LegendCinema()
        val orderAvengerTicket = WatchAvengerCommand(movies)
        val orderHunterGhostTicket = WatchHunterGhostCommand(movies)

        val ticketByABA = TicketOrderByABA()
        ticketByABA.bookAndPaid(orderAvengerTicket)
        ticketByABA.bookAndPaid(orderHunterGhostTicket)
        ticketByABA.buyTicketForClient()
    }

}