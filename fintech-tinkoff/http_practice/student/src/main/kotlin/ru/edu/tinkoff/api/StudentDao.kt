package ru.edu.tinkoff.api

import org.springframework.stereotype.Repository
import ru.edu.tinkoff.model.Student

@Repository
class StudentDao {

    private val students: List<Student> = listOf(
        Student(1, "Afanasy", "Ignatov", 4),
        Student(2, "Dmitri", "Antonov", 2),
        Student(3, "Kir", "Naoumov", 1),
        Student(4, "Abram", "Maksimov", 4),
        Student(5, "Arseni", "Sokolov", 5),
        Student(6, "Gleb", "Markov", 6),
        Student(7, "Dominika", "Petrov", 7),
        Student(8, "Rada", "Kozlov", 3),
        Student(9, "Tamila", "Borisov", 7),
        Student(10, "Sergej", "Lagounov", 1),
        Student(11, "Vyacheslav", "Nikolaev", 2)
    )

    fun getStudentIds(): List<Int> = students.map { it.studentId }

    fun getStudent(studentId: Int): Student? = students.find { it.studentId == studentId }

}
