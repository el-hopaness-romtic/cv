package ru.edu.tinkoff.model

data class StudentWithFaculty(
    val studentId: Int,
    val firstName: String,
    val lastName: String,
    val facultyId: Int,
    val facultyName: String,
    val universityName: String
)
