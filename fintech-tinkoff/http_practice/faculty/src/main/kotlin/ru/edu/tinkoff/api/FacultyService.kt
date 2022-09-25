package ru.edu.tinkoff.api

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ru.edu.tinkoff.model.Faculty

@Service
class FacultyService(
    private val facultyDao: FacultyDao
) {
    fun getFaculty(facultyId: Int): Faculty {
        return facultyDao.getFaculty(facultyId) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}
