package com.example.designpatterninkotlinjava.behavioral


interface FetchData {
    fun accept(computerPartVisitor: FetchDataPartVisitor)
}

interface FetchDataPartVisitor {
    fun visit(sql: Sql)
    fun visit(json: Json)
    fun visit(text: TextFile)
}

class Json : FetchData {
    override fun accept(fetchDataPartVisitor: FetchDataPartVisitor) {
        fetchDataPartVisitor.visit(this)
    }
}

class TextFile : FetchData {
    override fun accept(fetchDataPartVisitor: FetchDataPartVisitor) {
        fetchDataPartVisitor.visit(this)
    }
}

class Sql : FetchData {
    override fun accept(computerPartVisitor: FetchDataPartVisitor) {
        computerPartVisitor.visit(this)
    }
}

class FetchDataPartDisplayVisitor : FetchDataPartVisitor {
    override fun visit(sql: Sql) {
        println("Store data with SQL, as existing in our project!")
    }

    override fun visit(json: Json) {
        println("Store data as json!")
    }

    override fun visit(text: TextFile) {
        println("Store data in text file!")
    }
}