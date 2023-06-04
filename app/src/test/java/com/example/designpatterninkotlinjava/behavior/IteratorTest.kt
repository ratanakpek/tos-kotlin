package com.example.designpatterninkotlinjava.behavior

import com.example.designpatterninkotlinjava.behavioral.iterator.AdminInfo
import com.example.designpatterninkotlinjava.behavioral.iterator.Employee
import com.example.designpatterninkotlinjava.behavioral.iterator.sample.Inventory
import com.example.designpatterninkotlinjava.behavioral.iterator.sample.Item

import org.junit.Test

class IteratorTest {

    @Test
    fun iterator_test() {
        val employeeList = listOf(
            Employee("Dara Chan", 20),
            Employee("Makara Pich", 14),
            Employee("Pov Ponlok", 26)
        )

        val adminInfo = AdminInfo(employeeList.toMutableList())
        val itDepartment = adminInfo.iterator()
        while (itDepartment.hasNext()) {
            val item = itDepartment.next()
            println(item.name)
        }
    }
}

