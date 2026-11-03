package co.istad.restfulemployeeapi.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record UpdateEmployeeRequest(

        @NotBlank(message = "Name is required.")
        @Size(min = 2, max = 100, message = "Name length must be 2-100 characters.")

        String name,
        @Email(message = "Invalid email format.")
        String email,

        @Positive(message = "Salary must be greater than 0")
        @NotNull(message = "Salary is required")
        BigDecimal salary,

        @NotBlank(message = "Hire date is required.\n j Format: YYYY-MM-DD")
        String hireDate,

        @NotBlank(message = "Active status is required.")
        Boolean isActive

) {
}
