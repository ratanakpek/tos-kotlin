package com.example.delegate

class HeavyOperation {
    init {
        println("Heavy")
    }
}

class Student {
    val heavyOperation by lazy {
        HeavyOperation()
    }
}