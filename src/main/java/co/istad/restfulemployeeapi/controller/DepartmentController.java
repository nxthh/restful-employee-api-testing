package co.istad.restfulemployeeapi.controller;

import co.istad.restfulemployeeapi.dto.department.CreateDepartmentRequest;
import co.istad.restfulemployeeapi.dto.department.DepartmentResponse;
import co.istad.restfulemployeeapi.dto.department.DepartmentStatisticsResponse;
import co.istad.restfulemployeeapi.dto.employee.EmployeeResponse;
import co.istad.restfulemployeeapi.service.DepartmentService;
import co.istad.restfulemployeeapi.utils.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<DepartmentResponse> getAll() {
        return departmentService.getAllDepartment();
    }

    @PostMapping
    public DepartmentResponse createDepartment(
            @Valid @RequestBody CreateDepartmentRequest request
            ) {
        return departmentService.createDepartment(request);
    }

    @PutMapping("/{id}/status")
    public void updateDepartmentStatus(
            @PathVariable String id,
            @RequestParam Boolean isActive
    ) {

        departmentService.updateStatusById(id, isActive);

    }

    @GetMapping("/{id}/employees")
    public PageResponse<EmployeeResponse> getEmployeesByDepartmentId(
            @PathVariable String id,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String direction
    ) {
        return departmentService.getEmployeesByDepartmentId(id, page, size, sortBy, direction);
    }

    @GetMapping("/{id}/statistics")
    public DepartmentStatisticsResponse getDepartmentStatistics(@PathVariable String id) {
        return departmentService.getStatisticsById(id);
    }
}
