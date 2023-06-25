package com.example.designpatterninkotlinjava.structural

interface Image {
    fun display()
}

class ProxyImage(var fileName: String) : Image {
    private var realImage: RealImage? = null

    override fun display() {
        if (realImage == null) {
            realImage = RealImage(fileName)
        }
        println()
        println("Cache from proxy")
        realImage?.display()
    }
}

class RealImage(private var fileName: String) : Image {
    init {
        loadFileFromDisk(fileName)
    }

    private fun loadFileFromDisk(fileName: String) {
        println("Loading $fileName")
    }

    override fun display() {
        println("Displaying $fileName")
    }
}