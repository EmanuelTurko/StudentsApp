package com.example.assignment2_studentlist.Model

class Model {
    var students = mutableListOf<Student>()

    companion object {
        val shared = Model()
    }
    fun addStudent(student: Student) {
        students.add(student)
    }
}