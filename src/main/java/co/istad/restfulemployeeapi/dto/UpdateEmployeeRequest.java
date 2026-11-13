package co.istad.restfulemployeeapi.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateEmployeeRequest(

        @Size(min = 2, max = 100, message = "Name length must be 2-100 characters.")
        String name,

        @Email(message = "Invalid email format.")
        String email,

        @Positive(message = "Salary must be greater than 0")
        BigDecimal salary,

        @PastOrPresent(message = "Hire Date must not be future.")
        LocalDate hireDate,

        Boolean isActive
) {}
