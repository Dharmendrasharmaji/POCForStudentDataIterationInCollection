package com.two.demo.service;

import com.two.demo.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> getAllStudents();

    List<StudentDto> saveStudent(StudentDto student);

    List<StudentDto> getStudentsByBranch(String branch);

    List<StudentDto> getStudentsTop3EachBranch();

    List<StudentDto> getSexWiseTopperEachBranch();
}
