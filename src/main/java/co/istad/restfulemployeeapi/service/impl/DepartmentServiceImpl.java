package co.istad.restfulemployeeapi.service.impl;

import co.istad.restfulemployeeapi.domain.Department;
import co.istad.restfulemployeeapi.dto.department.CreateDepartmentRequest;
import co.istad.restfulemployeeapi.dto.department.DepartmentResponse;
import co.istad.restfulemployeeapi.mapper.DepartmentMapper;
import co.istad.restfulemployeeapi.repository.DepartmentRepository;
import co.istad.restfulemployeeapi.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentResponse> getAllDepartment() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    @Override
    public DepartmentResponse createDepartment(CreateDepartmentRequest request) {

        Department department = departmentMapper.fromCreateRequest(request);
        department.setIsActive(Boolean.TRUE);

        departmentRepository.save(department);

        return departmentMapper.toResponse(department);
    }

    @Override
    public void updateStatusById(String id, Boolean isActive) {

        Department department = departmentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Department has not been found"
                )
        );
        department.setIsActive(Boolean.FALSE);
        department.getEmployees()
                .forEach(
                        emp -> emp.setIsActive(Boolean.FALSE)
                );
        departmentRepository.save(department);
    }
}
