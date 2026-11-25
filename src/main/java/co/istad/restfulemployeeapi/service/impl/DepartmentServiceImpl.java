package co.istad.restfulemployeeapi.service.impl;

import co.istad.restfulemployeeapi.domain.Department;
import co.istad.restfulemployeeapi.domain.Employee;
import co.istad.restfulemployeeapi.dto.department.CreateDepartmentRequest;
import co.istad.restfulemployeeapi.dto.department.DepartmentResponse;
import co.istad.restfulemployeeapi.dto.department.DepartmentStatisticsResponse;
import co.istad.restfulemployeeapi.dto.employee.EmployeeResponse;
import co.istad.restfulemployeeapi.mapper.DepartmentMapper;
import co.istad.restfulemployeeapi.mapper.EmployeeMapper;
import co.istad.restfulemployeeapi.repository.DepartmentRepository;
import co.istad.restfulemployeeapi.repository.EmployeeRepository;
import co.istad.restfulemployeeapi.service.DepartmentService;
import co.istad.restfulemployeeapi.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    private static final List<String> SORTABLE_FIELDS =
            List.of("name", "email", "salary", "hireDate", "isActive");

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

    @Override
    public PageResponse<EmployeeResponse> getEmployeesByDepartmentId(
            String id, int page, int size, String sortBy, String direction) {

        if (page < 0 || size < 1) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    """
                            Invalid Page Request.
                            Page must be 0 or greater and Size must be greater than 0."""
            );
        }

        if (!SORTABLE_FIELDS.contains(sortBy)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid sortBy. Allowed values: " + String.join(", ", SORTABLE_FIELDS)
            );
        }

        if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid direction. Allowed values: asc, desc"
            );
        }

        getById(id);

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.fromString(direction), sortBy)
        );

        Page<Employee> employees = employeeRepository.findAllByDepartmentId(id, pageable);

        return PageResponse.<EmployeeResponse>builder()
                .content(employees.getContent().stream().map(employeeMapper::toResponse).toList())
                .pageNumber(employees.getNumber())
                .size(employees.getSize())
                .totalElements(employees.getTotalElements())
                .totalPages(employees.getTotalPages())
                .build();
    }

    @Override
    public DepartmentStatisticsResponse getStatisticsById(String id) {

        Department department = getById(id);

        long totalEmployees = employeeRepository.countByDepartmentId(id);
        long activeEmployees = employeeRepository.countByDepartmentIdAndIsActiveTrue(id);
        Double average = employeeRepository.getAverageSalaryByDepartmentId(id);

        BigDecimal averageSalary = (average == null)
                ? BigDecimal.ZERO.setScale(2)
                : BigDecimal.valueOf(average).setScale(2, RoundingMode.HALF_UP);

        return new DepartmentStatisticsResponse(
                department.getId(),
                department.getName(),
                totalEmployees,
                activeEmployees,
                averageSalary
        );
    }

    private Department getById(String id) {
        return departmentRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Department has not been found"
                        )
                );
    }

}
