package com.example.designpatterninkotlinjava.behavioral.state.kotlin

sealed class ActivityLifeCycle {
    object OnCreate : ActivityLifeCycle()

    object OnResume : ActivityLifeCycle()

    object OnDestroy : ActivityLifeCycle()
}

class MainActivity(private var state: ActivityLifeCycle = ActivityLifeCycle.OnCreate) {

    val isOnResume: Boolean
        get() = when (state) {
            is ActivityLifeCycle.OnResume -> true
            else -> false
        }

    fun onCreate() {
        state = ActivityLifeCycle.OnCreate
    }

    fun onResume() {
        state = ActivityLifeCycle.OnResume
    }

    fun onDestroy() {
        state = ActivityLifeCycle.OnDestroy
    }

    override fun toString() = "State : ${
        when (state) {
            is ActivityLifeCycle.OnCreate -> "Create"
            is ActivityLifeCycle.OnResume -> "Resume"
            is ActivityLifeCycle.OnDestroy -> "Destory"
        }
    }"

}

