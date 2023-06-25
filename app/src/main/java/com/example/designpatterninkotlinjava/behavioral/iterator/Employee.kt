package com.example.designpatterninkotlinjava.behavioral.iterator

class Employee(var name: String, var workYear: Int)

class AdminInfo(var employeeList: MutableList<Employee>) : Iterable<Employee> {
    override fun iterator(): Iterator<Employee> {
        return ITDepartment(this)
    }
}

class ITDepartment(var adminInfo: AdminInfo) : Iterator<Employee> {
    private var index: Int = 0

    override fun hasNext(): Boolean {
        val employees = adminInfo.employeeList
        return index < employees.size
    }

    override fun next(): Employee {
        val employeeList = adminInfo.employeeList
        return if (hasNext()) {
            val eachEmployee = employeeList[index++]
            if (eachEmployee.workYear > 18) {
                eachEmployee
            } else {
                next()
            }
        } else {
            Employee("", 0)
        }
    }
}