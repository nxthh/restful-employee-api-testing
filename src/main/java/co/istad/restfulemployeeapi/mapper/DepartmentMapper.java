package co.istad.restfulemployeeapi.mapper;

import co.istad.restfulemployeeapi.domain.Department;
import co.istad.restfulemployeeapi.dto.department.CreateDepartmentRequest;
import co.istad.restfulemployeeapi.dto.department.DepartmentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentResponse toResponse(Department department);

    Department fromCreateRequest(CreateDepartmentRequest request);

}
