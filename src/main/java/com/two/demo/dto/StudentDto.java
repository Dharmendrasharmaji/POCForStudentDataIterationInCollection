package com.two.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {


    private int id;

    @NotBlank(message = "Name should not be null. ")
    @Size(min = 2,max = 15,message = "Name expected size is b/w 2 to 15")
    private String name;

    @Min(value = 15,message = "Min age is 15")
    @Max(value = 40,message = "Max age is 40")
    @Digits(integer = 2,fraction = 0)
    private int age;

    @NotBlank
    @Size(min = 1,max = 1,message = "Either M or F.")
    private String sex;

    @NotBlank
    @Size(min = 2,max = 2,message = "Can be like letters (CS/IT/ME/EE/EC/CE)")
    private String branch;

    @Digits(integer = 2,fraction = 2,message = "Can be only two and two digits before and after decimal")
    private double percentage;
}
