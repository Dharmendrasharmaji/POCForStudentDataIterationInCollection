package com.two.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.two.demo.dto.StudentDto;
import com.two.demo.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    private List<StudentDto> studentDtos;

    @BeforeEach
    public void set() {
        studentDtos = new ArrayList(List.of(
                new StudentDto(1, "Dharmendra", 25, "M", "CS", 99.9),
                new StudentDto(2, "Laxmi", 21, "F", "CS", 92.3),
                new StudentDto(3, "Aviral", 27, "M", "CS", 89.22),
                new StudentDto(4, "Shalu", 24, "F", "CS", 80),

                new StudentDto(5, "Divya", 22, "F", "IT", 55.3),
                new StudentDto(6, "Dheeraj", 30, "M", "IT", 74.2),
                new StudentDto(7, "Ankita", 30, "F", "IT", 64.2),
                new StudentDto(8, "Aniket", 30, "M", "IT", 99.3)
        ));
    }

    @Test
    public void shouldReturnAllStudentsListDataWithStatusOk() throws Exception {

        when(studentService.getAllStudents()).thenReturn(studentDtos);

        MvcResult mvcResult = mockMvc.perform(get("/api/students")).andExpect(status().isOk()).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        List<StudentDto> responseList = objectMapper.readValue(response.getContentAsString(), List.class);

        Assertions.assertEquals(studentDtos.size(), responseList.size());

    }

}
