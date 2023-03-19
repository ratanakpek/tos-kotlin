package com.example.delegate

import kotlin.properties.Delegates

class ObservableDelegation {
    var myName: String by Delegates.observable("Kid") { _, old, new ->
        println("Old=$old, new=$new")
    }
}