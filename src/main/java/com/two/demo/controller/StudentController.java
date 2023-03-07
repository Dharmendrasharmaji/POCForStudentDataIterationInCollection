package com.two.demo.controller;

import com.two.demo.dto.StudentDto;
import com.two.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
//class level annotation to validate parameters that are passed into a method of the annotated class
@Validated
public class StudentController {

    //    autowiring service class
    @Autowired
    private StudentService studentService;

    //    Get all the students data stored
    @GetMapping("/students")
    public ResponseEntity<?> getAllStudentsDetail() {
        List<StudentDto> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    //    post student data with validation on @RequestBody
    @PostMapping("/student")
    public ResponseEntity<?> saveStudentsDetails(@Valid @RequestBody StudentDto student) {
        List<StudentDto> studentDtoList = studentService.saveStudent(student);
        return new ResponseEntity<>(studentDtoList, HttpStatus.ACCEPTED);
    }

    //    Get student data with the branch name provided as param
    @GetMapping("/students/branched")
    public ResponseEntity<?> getAllStudentsDetailByBranch(@RequestParam(value = "branch", required = false) String branch) {
        List<StudentDto> students = studentService.getStudentsByBranch(branch);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    //    Get top 3 student data each branch
    @GetMapping("/students/top3")
    public ResponseEntity<?> getTop3StudentsEachBranch() {
        List<StudentDto> students = studentService.getStudentsTop3EachBranch();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    //    Get sex wise topper student data each branch
    @GetMapping("/students/gender")
    public ResponseEntity<?> getSexWiseTopperStudentsEachBranch() {
        List<StudentDto> students = studentService.getSexWiseTopperEachBranch();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


}
