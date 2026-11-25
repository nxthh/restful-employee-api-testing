package co.istad.restfulemployeeapi.dto.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateEmployeeRequest(

        String name,

        String email,

        BigDecimal salary,

        LocalDate hireDate,

        Boolean isActive

) {
}
