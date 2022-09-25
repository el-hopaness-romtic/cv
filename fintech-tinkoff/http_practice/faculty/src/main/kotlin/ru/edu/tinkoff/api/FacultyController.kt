package ru.edu.tinkoff.api

import org.springframework.web.bind.annotation.*
import ru.edu.tinkoff.model.Faculty

@RestController
@RequestMapping("/faculty")
class FacultyController(
    private val facultyService: FacultyService
) {
    @GetMapping("/{facultyId}")
    fun getFaculty(@PathVariable facultyId: Int): Faculty = facultyService.getFaculty(facultyId)
}
