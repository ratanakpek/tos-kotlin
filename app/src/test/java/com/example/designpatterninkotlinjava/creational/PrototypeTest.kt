package com.example.designpatterninkotlinjava.creational

import com.example.designpatterninkotlinjava.creational.prototype.RoomDb
import com.example.designpatterninkotlinjava.creational.prototype.ServiceDataStore.getUserInfoCache
import com.example.designpatterninkotlinjava.creational.prototype.ServiceDataStore.loadCache
import com.example.designpatterninkotlinjava.creational.prototype.ServiceDataStore.userInfoCache
import org.junit.Assert
import org.junit.Test


class PrototypeTest {

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

}