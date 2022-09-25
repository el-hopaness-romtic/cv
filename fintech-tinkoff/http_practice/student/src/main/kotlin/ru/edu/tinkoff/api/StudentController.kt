package ru.edu.tinkoff.api

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.*
import ru.edu.tinkoff.model.Student
import ru.edu.tinkoff.model.StudentWithFaculty

@RestController
@RequestMapping("/student")
class StudentController(
    private val studentService: StudentService
) {
    @ApiOperation("Get list of all students")
    @ApiResponses(
        ApiResponse(code = 200, message = "OK", response = StudentWithFaculty::class, responseContainer = "List")
    )
    @GetMapping
    fun getAllStudents(): List<StudentWithFaculty> = studentService.getAllStudents()

    @ApiOperation("Get student with given id")
    @ApiResponses(
        ApiResponse(code = 200, message = "OK", response = StudentWithFaculty::class),
        ApiResponse(code = 404, message = "Not found")
    )
    @GetMapping("/{studentId}")
    fun getStudent(@ApiParam("Unique student id") @PathVariable studentId: Int): StudentWithFaculty =
        studentService.getStudent(studentId)

    @ApiOperation("Add student")
    @ApiResponses(ApiResponse(code = 200, message = "OK"))
    @PostMapping
    fun addStudent(@ApiParam("Student object") @RequestBody student: Student) {
        println("Student was added. Student = $student")
    }

    @ApiOperation("Change student with given id")
    @ApiResponses(
        ApiResponse(code = 200, message = "OK"),
        ApiResponse(code = 404, message = "Not found")
    )
    @PutMapping("/{studentId}")
    fun updateStudent(
        @ApiParam("Unique student id") @PathVariable studentId: Int,
        @ApiParam("Student object") @RequestBody student: Student
    ) {
        println("Student was updated. Student = $student, id = $studentId")
    }

    @ApiOperation("Delete student with given id")
    @ApiResponses(
        ApiResponse(code = 200, message = "OK"),
        ApiResponse(code = 404, message = "Not found")
    )
    @DeleteMapping("/{studentId}")
    fun deleteStudent(@ApiParam("Unique student id") @PathVariable studentId: Int) {
        println("Student with id = $studentId was deleted ")
    }
}
