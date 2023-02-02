package com.example.designpatterninkotlinjava.creational.prototype

import java.util.*

abstract class RoomDb : Cloneable {
    var id: String? = null
    var type: String? = null

    abstract fun store()

    public override fun clone(): Any {
        var clone: Any? = null
        try {
            clone = super.clone()
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }
        return clone!!
    }
}

class UserProfileCache : RoomDb() {
    init {
        type = "UserProfile"
    }

    override fun store() {
        println("Store userprofile cache!")
    }
}

class TokenCache : RoomDb() {
    init {
        type = "TokenCache"
    }

    override fun store() {
        println("Store token cache!")
    }
}

class ImageCache : RoomDb() {
    init {
        type = "ImageCache"
    }

    override fun store() {
        println("Store image cache!")
    }
}

object ServiceDataStore {
    val userInfoCache: Hashtable<String, RoomDb> = Hashtable<String, RoomDb>()

    fun getUserInfoCache(uniqueId: String?): RoomDb {
        val cachedInfo: RoomDb? = userInfoCache[uniqueId]
        return cachedInfo?.clone() as RoomDb
    }

    fun loadCache() {
        //heavy operation to get user profile from room and store in map as cache
        val profileCache = UserProfileCache()
        profileCache.apply {
            id = "1"
            userInfoCache[id] = profileCache
        }


        //heavy operation to get get token from server and store in map as cache
        val tokenCache = TokenCache()
        tokenCache.apply {
            id = "2"
            userInfoCache[id] = tokenCache
        }

        //heavy operation to get get ImageCache from server and store in map as cache
        val imageCache = ImageCache()
        imageCache.apply {
            id = "3"
            userInfoCache[id] = imageCache
        }

    }
}
