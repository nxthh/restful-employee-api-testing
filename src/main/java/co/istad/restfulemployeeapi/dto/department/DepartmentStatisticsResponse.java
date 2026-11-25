package co.istad.restfulemployeeapi.dto.department;

import java.math.BigDecimal;

public record DepartmentStatisticsResponse(

        String departmentId,

        String departmentName,

        long totalEmployees,

        long activeEmployees,

        BigDecimal averageSalary

) {
}
