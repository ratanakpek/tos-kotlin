package com.example.designpatterninkotlinjava.creational

import com.example.designpatterninkotlinjava.creational.prototype.GooglePixel
import com.example.designpatterninkotlinjava.creational.prototype.OperatingSystemAndroid
import com.example.designpatterninkotlinjava.creational.prototype.RoomDb
import com.example.designpatterninkotlinjava.creational.prototype.Samsung
import com.example.designpatterninkotlinjava.creational.prototype.ServiceDataStore.getUserInfoCache
import com.example.designpatterninkotlinjava.creational.prototype.ServiceDataStore.loadCache
import com.example.designpatterninkotlinjava.creational.prototype.ServiceDataStore.userInfoCache
import org.junit.Assert
import org.junit.Test


class PrototypeTest {

    @Test
    fun `Without use clone when we modify object, it will affect original test`() {
        val oldSamsung = Samsung()
        oldSamsung.apply {
            deviceId = "Samsung111"
            name = "Samsung Galaxy S22 Ultra 5g"
        }

        val newSamsung = oldSamsung
        newSamsung.apply {
            deviceId = "123"
            name = "haha"
        }

        //Both object had been modified and they are same value
        Assert.assertEquals(oldSamsung.deviceId, newSamsung.deviceId) //"123"
        Assert.assertEquals(oldSamsung.name, newSamsung.name) //"haha"
    }

    @Test
    fun `Use clone() to copy the original object, and we can modify it as want test`() {
        val oldSamsung = Samsung()
        oldSamsung.apply {
            deviceId = "Samsung111"
            name = "Samsung Galaxy S22 Ultra 5g"
        }

        val newSamsung = oldSamsung.clone()
        newSamsung.apply {
            deviceId = "123"
            name = "haha"
        }

        //They are different object
        Assert.assertNotEquals(oldSamsung.deviceId, newSamsung.deviceId)
        Assert.assertNotEquals(oldSamsung.name, newSamsung.name)
    }

    @Test
    fun `Use prototype pattern to store user profile cache success test`() {
        //store in cache
        loadCache()
        Assert.assertEquals(true, userInfoCache.size == 3)

        val clonedUserProfile: RoomDb = getUserInfoCache("1")
        //compare object: UserProfileCache@61c4eee0, clone object: UserProfileCache@6f10d5b6
        println("compare object: " + clonedUserProfile + ", clone object: " + userInfoCache["1"])
        //bec of clone, so both object are difference
        Assert.assertNotEquals(clonedUserProfile, userInfoCache["1"])
        println("clone object type : " + clonedUserProfile.type)
    }

    @Test
    fun `Use prototype pattern to store user token cache success test`() {
        //store in cache
        loadCache()
        Assert.assertEquals(true, userInfoCache.size == 3)

        val cloneToken: RoomDb = getUserInfoCache("2")
        //compare object: TokenCache@61c4eee0, clone object: TokenCache@6f10d5b6
        println("compare object: " + cloneToken + ", clone object: " + userInfoCache["2"])
        //bec of clone, so both object are difference
        Assert.assertNotEquals(cloneToken, userInfoCache["1"])
        println("clone object type : " + cloneToken.type)
    }


    @Test
    fun `Assign new var but still affect original object from map test`() {
        val reportItems: MutableMap<String, OperatingSystemAndroid> = mutableMapOf()
        val samsung = Samsung()
        reportItems["1"] = samsung.apply {
            deviceId = "Samsung111"
            name = "Samsung Galaxy S22 Ultra 5g"
        }

        val px = GooglePixel()
        reportItems["2"] = px.apply {
            deviceId = "google222"
            name = "Google Pixel 6 Pro"
        }

        //Junior think, I already create new variable but still affect original one
        val newSamsung: Samsung = reportItems["1"] as Samsung
        newSamsung.deviceId = "unknown"

        reportItems.forEach {
            if (it.value is Samsung) {
                println((it.value as Samsung).deviceId)
                Assert.assertNotEquals("Samsung111", (it.value as Samsung).deviceId)
                Assert.assertEquals("unknown", (it.value as Samsung).deviceId)
            } else if (it.value is GooglePixel) {
                println((it.value as GooglePixel).deviceId)
                Assert.assertEquals("google222", (it.value as GooglePixel).deviceId)
            }
        }
    }

    @Test
    fun `Clone object as new one test`() {
        val reportItems: MutableMap<String, OperatingSystemAndroid> = mutableMapOf()
        val samsung = Samsung()
        reportItems["1"] = samsung.apply {
            deviceId = "Samsung111"
            name = "Samsung Galaxy S22 Ultra 5g"
        }

        val px = GooglePixel()
        reportItems["2"] = px.apply {
            deviceId = "google222"
            name = "Google Pixel 6 Pro"
        }

        //Here we modify object with the new one that we just clone
        //no effect to original
        val newSamsung: Samsung = reportItems["1"]?.clone() as Samsung
        newSamsung.deviceId = "unknown"

        reportItems.forEach {
            if (it.value is Samsung) {
                println((it.value as Samsung).deviceId) //Samsung111
                Assert.assertEquals("Samsung111", (it.value as Samsung).deviceId)
            } else if (it.value is GooglePixel) {
                println((it.value as GooglePixel).deviceId) //google222
                Assert.assertEquals("google222", (it.value as GooglePixel).deviceId)
            }
        }
    }
}
