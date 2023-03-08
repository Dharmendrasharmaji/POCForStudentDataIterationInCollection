package com.two.demo;


import com.two.demo.dao.StudentDao;
import com.two.demo.dto.StudentDto;
import com.two.demo.exceptions.IdAlreadyExistsException;
import com.two.demo.exceptions.NoBranchPresentWithProvidedNameException;
import com.two.demo.exceptions.NoDataFoundException;
import com.two.demo.service.StudentService;
import com.two.demo.service.StudentServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceTest {

    private StudentService studentService;

    private List<StudentDto> localDto = new ArrayList<>(StudentDao.listStudent);

    @BeforeEach
    public void set() {

        studentService = new StudentServiceImpl();
    }

    @AfterEach
    public void cleanUp() {
        StudentDao.listStudent = localDto;
    }

    @Test
    public void shouldThrowExceptionForNoDataPresentWhileGettingAllStudents() {
        StudentDao studentDao = new StudentDao();
        studentDao.listStudent = new ArrayList<>();
        Assertions.assertThrows(NoDataFoundException.class, () -> studentService.getAllStudents());
    }

    @Test
    public void shouldReturnListOfAllStudents() {
        List<StudentDto> listStudent = StudentDao.listStudent;
        Assertions.assertEquals(listStudent, studentService.getAllStudents());
    }

    @Test
    public void shouldThrowExceptionIfIDAlreadyPresentWhileSavingStudent() {
        StudentDto studentDto = new StudentDto(3, "Dharmendra", 23, "M", "ME", 33.4);
        Assertions.assertThrows(IdAlreadyExistsException.class, () -> studentService.saveStudent(studentDto));
    }

    @Test
    public void shouldSaveStudentDataAndReturnAllUpdatedStudentsList() {
        StudentDto studentData = new StudentDto(16, "Dharmendra", 23, "M", "ME", 33.4);
        ArrayList<StudentDto> newList = new ArrayList<>(StudentDao.listStudent);
        newList.add(studentData);
        Assertions.assertEquals(newList, studentService.saveStudent(studentData));
    }

    @Test
    public void shouldReturnAllSavedDataIfIfBranchIsNullOrBlank() {
        Assertions.assertEquals(StudentDao.listStudent, studentService.getStudentsByBranch(""));
    }

    @Test
    public void shouldThrowExceptionIfBranchIsAbsent() {
        Assertions.assertThrows(NoBranchPresentWithProvidedNameException.class, () -> studentService.getStudentsByBranch("TC"));
    }

    @Test
    public void shouldReturnListOfStudentsByBranch() {
        List<StudentDto> me = StudentDao.listStudent.stream().filter(s -> s.getBranch().equals("ME")).collect(Collectors.toList());

        Assertions.assertEquals(me, studentService.getStudentsByBranch("ME"));
    }

    @Test
    public void shouldThrowExceptionIfNoDataFoundForTop3Student() {
        StudentDao.listStudent = new ArrayList<>();
        Assertions.assertThrows(NoDataFoundException.class, () -> studentService.getStudentsTop3EachBranch());
    }

    @Test
    public void shouldReturnListOfTop3StudentsFromEachBranch() {

        Assertions.assertSame(ArrayList.class, studentService.getStudentsTop3EachBranch().getClass());

    }

    @Test
    public void shouldThrowExceptionIfNoDataFoundForSexWiseTopperEachBranc() {
        StudentDao.listStudent = new ArrayList<>();
        Assertions.assertThrows(NoDataFoundException.class, () -> studentService.getSexWiseTopperEachBranch());
    }


    @Test
    public void shouldReturnListOfSexWiseTopperEachBranch() {

        Assertions.assertSame(ArrayList.class, studentService.getSexWiseTopperEachBranch().getClass());

    }


}
