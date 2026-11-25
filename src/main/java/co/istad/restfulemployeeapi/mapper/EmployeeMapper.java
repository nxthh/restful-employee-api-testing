package co.istad.restfulemployeeapi.mapper;

import co.istad.restfulemployeeapi.domain.Employee;
import co.istad.restfulemployeeapi.dto.employee.CreateEmployeeRequest;
import co.istad.restfulemployeeapi.dto.employee.EmployeeResponse;
import co.istad.restfulemployeeapi.dto.employee.UpdateEmployeeRequest;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {DepartmentMapper.class}
)
public interface EmployeeMapper {

    // Map from Domain to DTO
    @Mapping(target = "departmentId", source = "department.id")
    EmployeeResponse toResponse(Employee employee);

    // Map from DTO to Domain
    Employee fromCreateEmployeeRequest(CreateEmployeeRequest createEmployeeRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Employee employee,
                      UpdateEmployeeRequest request);

}
