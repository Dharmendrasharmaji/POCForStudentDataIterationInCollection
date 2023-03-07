package com.two.demo.service;

import com.two.demo.dto.StudentDto;

import java.util.List;

public interface StudentService {

    //    interface to get all student data stored
    List<StudentDto> getAllStudents();

    //    interface to save student data
    List<StudentDto> saveStudent(StudentDto student);

    //    interface to get student data by branch name
    List<StudentDto> getStudentsByBranch(String branch);

    //    interface to get top 3 students each brach
    List<StudentDto> getStudentsTop3EachBranch();

    //    interface to get sex wise topper each branch
    List<StudentDto> getSexWiseTopperEachBranch();
}
