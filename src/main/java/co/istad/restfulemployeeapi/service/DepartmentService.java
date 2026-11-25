package co.istad.restfulemployeeapi.service;

import co.istad.restfulemployeeapi.dto.department.CreateDepartmentRequest;
import co.istad.restfulemployeeapi.dto.department.DepartmentResponse;
import co.istad.restfulemployeeapi.dto.department.DepartmentStatisticsResponse;
import co.istad.restfulemployeeapi.dto.employee.EmployeeResponse;
import co.istad.restfulemployeeapi.utils.PageResponse;

import java.util.List;

public interface DepartmentService {

    List<DepartmentResponse> getAllDepartment();

    DepartmentResponse createDepartment(
            CreateDepartmentRequest request
    );

    void updateStatusById(String id, Boolean isActive);

    PageResponse<EmployeeResponse> getEmployeesByDepartmentId(
            String id, int page, int size, String sortBy, String direction
    );

    DepartmentStatisticsResponse getStatisticsById(String id);
}
