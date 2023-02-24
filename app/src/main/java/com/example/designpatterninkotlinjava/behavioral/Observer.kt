package com.example.designpatterninkotlinjava.behavioral

import kotlin.properties.Delegates

class ObservableInKotlin {
    //Kotlin able to observable another type like boolean, double, float, ...
    var text: String by Delegates.observable("<Initialize value>") { _, oldValue, newValue ->
        println("Old= $oldValue, New= $newValue")
    }
}
