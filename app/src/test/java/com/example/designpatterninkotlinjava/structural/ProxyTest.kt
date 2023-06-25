package com.example.designpatterninkotlinjava.structural

import org.junit.Test

class ProxyTest {
    @Test
    fun `Demo of proxy pattern test`() {
        val image = ProxyImage("my_vdo.mp4")
        //image will load from original of the disk
        image.display()

        //image will load form proxy instead
        image.display()
    }
}
