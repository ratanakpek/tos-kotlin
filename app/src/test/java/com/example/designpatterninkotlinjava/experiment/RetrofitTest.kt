package com.example.designpatterninkotlinjava.experiment

import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


class RetrofitTest {

    @Test
    fun doTest() {
        //val retrofit = RetrofitSingleton.profileApi.listRepos("")?.enqueue(object : Call<String>)
    }

}

object RetrofitSingleton {
    private const val BASE_URL = "https://test.com"

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val profileApi: GitHubService by lazy {
        retrofit.create(GitHubService::class.java)
    }
}

interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<Repo?>?>?
}

data class Repo(var id: Int)

