package com.example.designpatterninkotlinjava.structural

class Employee(
    var name: String,
    var dept: String,
    var salary: Int
) {
    var subordinates = mutableListOf<Employee>()

    //for demo purpose
    override fun toString(): String {
        return "Employee :[ Name : $name, dept : $dept, salary :$salary ]"
    }
}
