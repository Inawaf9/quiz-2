package com.nawaf.quiz2.Model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    @NotEmpty(message = "Id required")
    @Size(min = 2, message = "Id must be 2 or more characters")
    private String id;

    @NotEmpty(message = "Name required")
    @Size(max = 25, message = "Name must be less than 25 characters")
    private String name;

    @NotNull(message = "Age required")
    @Min(value = 18, message = "Age cannot be smaller than 18")
    private int age;

    @NotEmpty(message = "Major required")
    @Size(max = 50, message = "Major cannot be more than 50 characters")
    private String major;
}
