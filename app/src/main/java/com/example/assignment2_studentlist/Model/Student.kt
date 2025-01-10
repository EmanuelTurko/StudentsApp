package com.example.assignment2_studentlist.Model

data class Student(
    val name: String,
    val id: String,
    val phone: String,
    val address: String,
    val avatarURL: String,
    var isChecked: Boolean
)
