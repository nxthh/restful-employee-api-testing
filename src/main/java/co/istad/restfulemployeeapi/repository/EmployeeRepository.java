package co.istad.restfulemployeeapi.repository;

import co.istad.restfulemployeeapi.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByEmail(String email);

}
