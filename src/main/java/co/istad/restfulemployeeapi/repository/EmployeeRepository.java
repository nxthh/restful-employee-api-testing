package co.istad.restfulemployeeapi.repository;

import co.istad.restfulemployeeapi.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Integer id);

    List<Employee> findAllByNameContainingIgnoreCase(String name);
}
