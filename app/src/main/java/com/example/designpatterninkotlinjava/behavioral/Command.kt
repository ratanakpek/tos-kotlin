package com.example.designpatterninkotlinjava.behavioral


/**
 * Behavioral design patterns
 *  - object should be able to talk to each other and still be loosely coupled.
 *
 *  here is Command Pattern in this category
 */

//command
interface MovieTicketOrder {
    fun orderNow()
}

//Create a request class
class LegendCinema {
    fun avengerMovie() {
        println("Movie : Avenger Ticket has been ordered!")
    }

    fun hunterGhost() {
        println("Movie : HunterGhost Ticket has been ordered!")
    }
}

//Command for buying ticket of Avenger
class WatchAvengerCommand(var cinema: LegendCinema) : MovieTicketOrder {
    override fun orderNow() {
        cinema.avengerMovie()
    }
}

//Command for buying ticket of HunterGhost
class WatchHunterGhostCommand(var cinema: LegendCinema) : MovieTicketOrder {
    override fun orderNow() {
        cinema.hunterGhost()
    }
}

//Invoker class
class TicketOrderByABA {
    private var bookTickets = mutableListOf<MovieTicketOrder>()

    fun bookAndPaid(orderTicket: MovieTicketOrder) {
        bookTickets.add(orderTicket)
    }

    fun buyTicketForClient() {
        //send order info to merchant
        bookTickets.forEach { it.orderNow() }

        //clear orders
        bookTickets.clear()
    }
}