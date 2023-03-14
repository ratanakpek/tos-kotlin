package com.example.delegate

import com.example.delegate.byinterface.CustomView
import com.example.delegate.byinterface.View
import org.junit.Test

class DelegationByUsingInterfaceTest {
    @Test
    fun delegation_using_interface_test() {
        val customView = CustomView()
        val view = View(customView)
        view.show()
    }
}



