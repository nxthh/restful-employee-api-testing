package co.istad.restfulemployeeapi.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 150)
    private String email;

    @Column(precision = 10, scale = 2)
    private BigDecimal salary;

    @Column(nullable = false)
    private LocalDate hireDate;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn(
            name = "department_id",
            referencedColumnName = "id"
    )
    private Department department;

}
