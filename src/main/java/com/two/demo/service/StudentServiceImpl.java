package com.two.demo.service;

import com.two.demo.dao.StudentDao;
import com.two.demo.dto.StudentDto;
import com.two.demo.exceptions.IdAlreadyExistsException;
import com.two.demo.exceptions.NoBranchPresentWithProvidedNameException;
import com.two.demo.exceptions.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    //    autowiring studentdao for retrieval of student data stored
    @Autowired
    private StudentDao studentDao;

    //    service overriden method to get all stored data if not throw
    @Override
    public List<StudentDto> getAllStudents() {

        //      if list of student dao data is empty throw exception or else return student list
        if (studentDao.listStudent.isEmpty())
            throw new NoDataFoundException("No saved data.");
        return studentDao.listStudent;
    }

    //    service overriden method to save student in dao
    @Override
    public List<StudentDto> saveStudent(StudentDto student) {

//        retrieve student data from dao class
        List<StudentDto> savedList = StudentDao.listStudent;

//        check if any matching id
        boolean b = savedList.stream().anyMatch(s -> s.getId() == student.getId());

//        if id matched then throw exception saying id already exists
        if (b)
            throw new IdAlreadyExistsException("ID already present .");

//        if no matching id then save the data
        savedList.add(student);

//        return all student data
        return savedList;
    }

    //    service overriden method to get student data as a branch name
    @Override
    public List<StudentDto> getStudentsByBranch(String branch) {

//        if branch is null then return all data if not then search
        if (branch == null || branch.isBlank()) {
            return studentDao.listStudent;

        }
//        if no matching branch found throw exception
        else if (!studentDao.listStudent.stream().anyMatch(l -> l.getBranch().equals(branch))) {
            throw new NoBranchPresentWithProvidedNameException("Branch is not present.");
        } else {

//            trim branch name
            final String trimmedBranch = branch.trim();

//            take data from dao
            List<StudentDto> listStudent = studentDao.listStudent;

//            filter data according to the matched branch name
            List<StudentDto> fileteredStudents = listStudent.stream().filter(s -> s.getBranch().equals(trimmedBranch)).collect(Collectors.toList());

            return fileteredStudents;
        }


    }

    //    service overriden method to get top 3 students each branch
    @Override
    public List<StudentDto> getStudentsTop3EachBranch() {

//        get available branches
        List<String> distinctBranches = studentDao.listStudent.stream()
                .map(b -> b.getBranch())
                .distinct()
                .collect(Collectors.toList());

//        sort saved student data according to the percentage in descending order
        List<StudentDto> sortedStudentData = studentDao.listStudent.stream()
                .sorted(Comparator.comparing(StudentDto::getPercentage).reversed())
                .collect(Collectors.toList());

//        filter available branches with sorted list of student data with the limit of 3
        List<StudentDto> collect = distinctBranches.stream().flatMap(c ->
                sortedStudentData.stream()
                        .filter(m -> m.getBranch().equals(c))
                        .limit(3)
        ).collect(Collectors.toList());

//        if no data is present then throw exception
        if (collect.isEmpty()) {
            throw new NoDataFoundException("No data Found.");
        }

//        returning top 3 students each branch as a list
        return collect;
    }


    //    service overriden method to get sex wise topper in each branch one male and one female
    @Override
    public List<StudentDto> getSexWiseTopperEachBranch() {

//        list of M and F gender
        ArrayList<String> gender = new ArrayList<>(List.of("M", "F"));

//        list of distinct available branches
        List<String> distinctBranches = studentDao.listStudent.stream().map(b -> b.getBranch()).distinct().collect(Collectors.toList());

//        list of sorted student saved data in descending order of percentage
        List<StudentDto> sortedStudentData = studentDao.listStudent.stream().sorted(Comparator.comparing(StudentDto::getPercentage).reversed()).collect(Collectors.toList());

//        filter gender list in distinct branches with saved sorted data list of students to get sex wise toppers
        List<StudentDto> sexWiseListOfToppers = gender.stream().flatMap(g -> distinctBranches.stream().flatMap(d ->
                sortedStudentData.stream().filter(s -> s.getBranch().equals(d)).filter(s -> s.getSex().equals(g)).limit(1)
        )).collect(Collectors.toList());

//        if no data is found then throw exception
        if (sexWiseListOfToppers.isEmpty()){
            throw new NoDataFoundException("No Data Found.");
        }
//        returning sex wise toppers list of students
        return sexWiseListOfToppers;
    }


}
