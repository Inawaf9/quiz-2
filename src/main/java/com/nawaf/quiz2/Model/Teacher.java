package com.nawaf.quiz2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {

    @NotEmpty(message = "Id required")
    @Size(min = 2, message = "Id must be more than 2 characters")
    private String id;

    @NotEmpty(message = "Name required")
    @Size(max = 25, message = "Name cannot be more than 25 characters")
    private String name;

    @NotNull(message = "Salary required")
    @Positive(message = "Salary must be positive number")
    private double salary;
}
