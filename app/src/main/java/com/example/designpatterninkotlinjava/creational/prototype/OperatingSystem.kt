package com.example.designpatterninkotlinjava.creational.prototype

interface OperatingSystemAndroid {
    fun clone(): OperatingSystemAndroid
}

class Samsung : OperatingSystemAndroid {
    var deviceId: String? = null
    var name: String? = null

    constructor()

    constructor(source: Samsung) {
        this.deviceId = source.deviceId
        this.name = source.name
    }

    override fun clone(): Samsung {
        return Samsung(this)
    }
}

class GooglePixel : OperatingSystemAndroid {
    var deviceId: String? = null
    var name: String? = null

    constructor()
    constructor(source: GooglePixel) {
        this.deviceId = source.deviceId
        this.name = source.name
    }

    override fun clone(): GooglePixel {
        return GooglePixel(this)
    }
}
