package com.example.delegate

import com.example.delegate.byclass.Screen
import com.example.delegate.byclass.View
import org.junit.Test

class DelegationByUsingClassTest {
    @Test
    fun delegation_using_class_test() {
        val view = View()
        val screen = Screen(view)
        screen.show()
    }
}



