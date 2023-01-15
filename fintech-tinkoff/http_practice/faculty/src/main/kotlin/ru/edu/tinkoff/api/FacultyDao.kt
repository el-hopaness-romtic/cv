package ru.edu.tinkoff.api

import org.springframework.stereotype.Repository
import ru.edu.tinkoff.model.Faculty

@Repository
class FacultyDao {

    private val faculties: List<Faculty> = listOf(
        Faculty(1, "Faculty of Mathematics", "Higher School of Economics"),
        Faculty(2, "Faculty of World Economy and International Affairs", "Higher School of Economics"),
        Faculty(3, "School of Foreign Languages", "Higher School of Economics"),
        Faculty(4, "Faculty of Computational Mathematics and Cybernetics", "Moscow State University"),
        Faculty(5, "School of Business Administration", "Moscow State University"),
        Faculty(6, "Department of Problems of Physics and Energetics", "Moscow Institute of Physics and Technology"),
        Faculty(7, "Department of Innovation and High Technology", "Moscow Institute of Physics and Technology")
    )

    fun getFaculty(facultyId: Int): Faculty? = faculties.find { it.facultyId == facultyId }
}
