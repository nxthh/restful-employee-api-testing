package co.istad.restfulemployeeapi.service.impl;

import co.istad.restfulemployeeapi.domain.Employee;
import co.istad.restfulemployeeapi.dto.CreateEmployeeRequest;
import co.istad.restfulemployeeapi.dto.EmployeeResponse;
import co.istad.restfulemployeeapi.dto.UpdateEmployeeRequest;
import co.istad.restfulemployeeapi.mapper.EmployeeMapper;
import co.istad.restfulemployeeapi.repository.EmployeeRepository;
import co.istad.restfulemployeeapi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;


    @Override
    public List<EmployeeResponse> getAll() {

        List<Employee> employees = repository.findAll();

        return employees
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public EmployeeResponse getEmployeeById(Integer id)
    {

//        Employee foundEmployee = repository.findById(id)
//                .orElseThrow(
//                        () -> new ResponseStatusException(
//                                HttpStatus.NOT_FOUND,
//                                "Employee not found."
//                        )
//                );
//
//        return mapper.toResponse(foundEmployee);

        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Employee not found."
                        )
                );

    }

    @Override
    public EmployeeResponse createEmployee(CreateEmployeeRequest createEmployeeRequest) {

        if (repository.existsByEmail(createEmployeeRequest.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email is already existed."
            );
        }

        Employee employee = mapper.fromCreateEmployeeRequest(createEmployeeRequest);
        employee.setIsActive(Boolean.TRUE);

        repository.save(employee);

        return mapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(Integer id, UpdateEmployeeRequest updateEmployeeRequest) {
        Employee employee = repository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Employee not found."
                        )
                );

        if (updateEmployeeRequest.email() != null && repository.existsByEmailAndIdNot(updateEmployeeRequest.email(), id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email is already existed."
            );
        }

        mapper.updateEntity(employee, updateEmployeeRequest);

        repository.save(employee);

        return mapper.toResponse(employee);
    }

    @Override
    public void deleteEmployeeById(Integer id) {

        if (!repository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Employee not found."
            );
        }

        repository.deleteById(id);

    }

    @Override
    public List<EmployeeResponse> findEmployeesByName(String name) {

        List<Employee> employees = repository
                .findAllByNameContainingIgnoreCase(name);

        return employees.stream()
                .map(mapper::toResponse)
                .toList();
    }
}


