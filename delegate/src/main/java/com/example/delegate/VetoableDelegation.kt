package com.example.delegate

import kotlin.properties.Delegates

class VetoableDelegation {
    var myAge by Delegates.vetoable(18) { _, old, new ->
        println("Old=$old, new=$new")
        new >= 18
    }
}