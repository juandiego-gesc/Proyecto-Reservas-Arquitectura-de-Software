package com.farjuce.appreservas.bd.brach;

import com.farjuce.appreservas.bd.employee.Employee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "branch")
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String type;

    @Column
    private LocalTime openingTime;

    @Column
    private LocalTime closureTime;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Employee> employees;

}
