package co.istad.restfulemployeeapi.dto;

import jakarta.validation.constraints.*;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateEmployeeRequest(

        @NotBlank(message = "Name is required.")
        @Size(min = 2, max = 100, message = "Name length must be 2-100 characters.")
        String name,

        @NotBlank(message = "Email is required.")
        @Email(message = "Invalid email format.")
        String email,

        @Positive(message = "Salary must be greater than 0")
        @NotNull(message = "Salary is required")
        BigDecimal salary,

        @NotNull(message = "Hire Date is required.")
        @PastOrPresent(message = "Hire Date must not be future.")
        LocalDate hireDate

) {
}
