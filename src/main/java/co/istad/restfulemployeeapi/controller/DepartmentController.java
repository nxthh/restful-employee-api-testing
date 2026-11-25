package co.istad.restfulemployeeapi.controller;

import co.istad.restfulemployeeapi.dto.department.CreateDepartmentRequest;
import co.istad.restfulemployeeapi.dto.department.DepartmentResponse;
import co.istad.restfulemployeeapi.service.DepartmentService;
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
}
