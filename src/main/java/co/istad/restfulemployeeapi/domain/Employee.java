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
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            nullable = false
    )
    private String name;

    @Column(
            nullable = false,
            unique = true
    )

    private String email;

    @Column(
            nullable = false,
            check = @CheckConstraint(
                    constraint = "salary > 0"
            ),
            precision = 10,
            scale = 2
    )
    private BigDecimal salary;

    private LocalDate hireDate;

    private Boolean isActive;

}
