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
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("/students")
    public ResponseEntity<?> getAllStudentsDetail() {
        List<StudentDto> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<?> saveStudentsDetails(@Valid @RequestBody StudentDto student) {
        List<StudentDto> studentDtoList = studentService.saveStudent(student);
        return new ResponseEntity<>(studentDtoList, HttpStatus.ACCEPTED);
    }

    @GetMapping("/students/branched")
    public ResponseEntity<?> getAllStudentsDetailByBranch(@RequestParam(value = "branch",required = false) String branch) {
        List<StudentDto> students = studentService.getStudentsByBranch(branch);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/top3")
    public ResponseEntity<?> getTop3StudentsEachBranch() {
        List<StudentDto> students = studentService.getStudentsTop3EachBranch();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/gender")
    public ResponseEntity<?> getSexWiseTopperStudentsEachBranch() {
        List<StudentDto> students = studentService.getSexWiseTopperEachBranch();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }



}
