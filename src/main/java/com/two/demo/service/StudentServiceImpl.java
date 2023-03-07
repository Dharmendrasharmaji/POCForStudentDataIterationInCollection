package com.two.demo.service;

import com.two.demo.dao.StudentDao;
import com.two.demo.dto.StudentDto;
import com.two.demo.exceptions.IdAlreadyExistsException;
import com.two.demo.exceptions.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<StudentDto> getAllStudents() {
        if (studentDao.listStudent.isEmpty())
            throw new NoDataFoundException("No saved data.");
        return studentDao.listStudent;
    }

    @Override
    public List<StudentDto> saveStudent(StudentDto student) {


        List<StudentDto> savedList = StudentDao.listStudent;

        boolean b = savedList.stream().anyMatch(s -> s.getId() == student.getId());

        if (b)
            throw new IdAlreadyExistsException("ID already present .");

        savedList.add(student);

        return savedList;
    }

    @Override
    public List<StudentDto> getStudentsByBranch(String branch) {
        if (branch == null) {
            return studentDao.listStudent;

        } else {
            final String trimmedBranch = branch.trim();
            List<StudentDto> listStudent = studentDao.listStudent;
            System.out.println(branch);
            List<StudentDto> fileteredStudents = listStudent.stream().filter(s -> s.getBranch().equals(trimmedBranch)).collect(Collectors.toList());
            if (fileteredStudents.isEmpty())
                throw new NoDataFoundException("No match found with " + trimmedBranch);
            return fileteredStudents;
        }


    }

    @Override
    public List<StudentDto> getStudentsTop3EachBranch() {
        List<String> distinctBranches = studentDao.listStudent.stream().map(b -> b.getBranch()).distinct().collect(Collectors.toList());

        List<StudentDto> sortedStudentData = studentDao.listStudent.stream().sorted(Comparator.comparing(StudentDto::getPercentage).reversed()).collect(Collectors.toList());


        List<StudentDto> collect = distinctBranches.stream().flatMap(c ->
                sortedStudentData.stream().filter(m -> m.getBranch().equals(c)).limit(3)
        ).collect(Collectors.toList());

        return collect;
    }

    @Override
    public List<StudentDto> getSexWiseTopperEachBranch() {
        ArrayList<String> gender = new ArrayList<>(List.of("M", "F"));

        List<String> distinctBranches = studentDao.listStudent.stream().map(b -> b.getBranch()).distinct().collect(Collectors.toList());

        List<StudentDto> sortedStudentData = studentDao.listStudent.stream().sorted(Comparator.comparing(StudentDto::getPercentage).reversed()).collect(Collectors.toList());

        List<StudentDto> sexWiseListOfToppers = gender.stream().flatMap(g -> distinctBranches.stream().flatMap(d ->
                sortedStudentData.stream().filter(s -> s.getBranch().equals(d)).filter(s -> s.getSex().equals(g)).limit(1)
        )).collect(Collectors.toList());

        return sexWiseListOfToppers;
    }


}
