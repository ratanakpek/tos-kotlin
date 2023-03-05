package com.example.designpatterninkotlinjava.behavior

import com.example.designpatterninkotlinjava.behavioral.Sql
import com.example.designpatterninkotlinjava.behavioral.FetchDataPartDisplayVisitor
import com.example.designpatterninkotlinjava.behavioral.Json
import com.example.designpatterninkotlinjava.behavioral.TextFile

import org.junit.Test

class VisitorTest {

    @Test
    fun visitor_test_demo() {
        val displayVisitor = FetchDataPartDisplayVisitor()
        //This is old way to store data in sql
        Sql().apply {
            accept(displayVisitor)
        }

        //new way to store data as json
        Json().apply {
            accept(displayVisitor)
        }

        //new way to store data text file instead
        TextFile().apply {
            accept(displayVisitor)
        }
    }
}

