package com.two.demo.dao;

import com.two.demo.dto.StudentDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// student dao class with list of Student objects
@Component
public class StudentDao {

    public static List<StudentDto> listStudent = new ArrayList(List.of(
            new StudentDto(1, "Dharmendra", 25, "M", "CS", 99.9),
            new StudentDto(2, "Laxmi", 21, "F", "CS", 92.3),
            new StudentDto(3, "Aviral", 27, "M", "CS", 89.22),
            new StudentDto(4, "Shalu", 24, "F", "CS", 80),

            new StudentDto(5, "Divya", 22, "F", "IT", 55.3),
            new StudentDto(6, "Dheeraj", 30, "M", "IT", 74.2),
            new StudentDto(7, "Ankita", 30, "F", "IT", 64.2),
            new StudentDto(8, "Aniket", 30, "M", "IT", 99.3),

            new StudentDto(9, "Shivi", 30, "F", "ME", 44.5),
            new StudentDto(10, "Jyoti", 30, "F", "ME", 53.4),
            new StudentDto(11, "Shivam", 30, "M", "ME", 93.4),
            new StudentDto(12, "Shelesh", 30, "M", "ME", 95.3),
            new StudentDto(13, "Avirat", 30, "M", "ME", 99)

    ));


}
