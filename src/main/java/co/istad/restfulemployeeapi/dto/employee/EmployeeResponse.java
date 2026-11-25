package co.istad.restfulemployeeapi.dto.employee;

import co.istad.restfulemployeeapi.dto.department.DepartmentResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeResponse(

    String id,

    String name,

    String email,

    BigDecimal salary,

    LocalDate hireDate,

    Boolean isActive,

    DepartmentResponse department,

    String departmentId

) {
}
