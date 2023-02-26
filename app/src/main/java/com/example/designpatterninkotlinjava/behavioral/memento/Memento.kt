package com.example.designpatterninkotlinjava.behavioral.memento

data class Memento(val state: String)

class Originator(var state: String) {
    fun createMemento() = Memento(state)
    fun restore(memento: Memento) {
        state = memento.state
    }
}

class TextEditor {
    private val mementoList = mutableListOf<Memento>()
    fun saveState(state: Memento) {
        mementoList.add(state)
    }

    fun restore(index: Int) = mementoList[index]
}