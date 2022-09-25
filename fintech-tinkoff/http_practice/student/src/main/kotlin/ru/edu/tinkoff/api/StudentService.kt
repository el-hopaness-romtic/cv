package ru.edu.tinkoff.api

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException
import ru.edu.tinkoff.model.Faculty
import ru.edu.tinkoff.model.StudentWithFaculty

@Service
class StudentService(
    private val studentDao: StudentDao
) {
    private val restTemplate = RestTemplate()

    fun getAllStudents(): List<StudentWithFaculty> = studentDao.getStudentIds().map { getStudent(it) }

    fun getStudent(studentId: Int): StudentWithFaculty {
        val student = studentDao.getStudent(studentId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val faculty = restTemplate.getForObject(
            "http://localhost:8081/faculty/${student.facultyId}",
            Faculty::class.java
        )!!

        return StudentWithFaculty(
            student.studentId,
            student.firstName,
            student.lastName,
            faculty.facultyId,
            faculty.facultyName,
            faculty.universityName
        )
    }
}
