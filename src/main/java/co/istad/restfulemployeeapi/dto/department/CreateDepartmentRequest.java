package co.istad.restfulemployeeapi.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateDepartmentRequest (

        @NotBlank(message = "Name is required")
        @Size(min = 5, max = 50)
        String name,

        String description

) {
}
