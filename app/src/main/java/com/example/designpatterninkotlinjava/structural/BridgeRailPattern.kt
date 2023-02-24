package com.example.designpatterninkotlinjava.structural

interface Train {
    fun move()
}

//1 single track road
open class Rail : Train {
    override fun move() {
        println("use 2 track")
    }
}

class ElectronicRail : Rail()
class DieselRail : Rail()
class GasolineRail : Rail()
class FlyingRail : Rail()
class WalkingRail : Rail()

//2 double track road
open class MonoRail : Train {
    override fun move() {
        println("use 1 track")
    }
}

class ElectronicMonoRail : MonoRail()
class DieselMonoRail : MonoRail()
class FlyingMonoRail : MonoRail()
class WalkingMonoRail : MonoRail()

//3 triple track road
open class TripleRail : Train {
    override fun move() {
        println("use 3 track")
    }
}

class ElectronicTripleRail : TripleRail()
class DieselTripleRail : TripleRail()
class FlyingTripleRail : TripleRail()
class WalkingTripleRail : TripleRail()


//Solution
interface ITrain {
    fun move(engine: Accelerable)
}

interface Accelerable {
    fun accelerate()
}

class MonoRail2 : ITrain {
    override fun move(engine: Accelerable) {
        engine.accelerate()
    }
}

class Rail2 : ITrain {
    override fun move(engine: Accelerable) {
        engine.accelerate()
    }
}

class ElectronicTripleRail2 : Accelerable {
    override fun accelerate() {

    }

}

class DieselTripleRail2 : Accelerable {
    override fun accelerate() {

    }

}
