package co.istad.restfulemployeeapi.dto.employee;

import jakarta.validation.constraints.NotBlank;

public record TransferEmployeeRequest(

        @NotBlank(message = "Department id is required")
        String departmentId

) {
}
