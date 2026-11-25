package co.istad.restfulemployeeapi.service;

import co.istad.restfulemployeeapi.dto.employee.CreateEmployeeRequest;
import co.istad.restfulemployeeapi.dto.employee.EmployeeResponse;
import co.istad.restfulemployeeapi.dto.employee.UpdateEmployeeRequest;
import co.istad.restfulemployeeapi.utils.PageResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    // CRUD: Create, Read, Update, Delete
    List<EmployeeResponse> getAll();
    EmployeeResponse getEmployeeById(String id);

    EmployeeResponse createEmployee(CreateEmployeeRequest employeeRequest);

    EmployeeResponse updateEmployeeById(String id, UpdateEmployeeRequest updateEmployeeRequest);

    void deleteEmployeeById(String id);

    void updateEmployeeStatus(String id, Boolean status);

    EmployeeResponse bulkCreate(@Valid List<CreateEmployeeRequest> createRequest);

    PageResponse<EmployeeResponse> getAllWithPage(int page, int size);
}
