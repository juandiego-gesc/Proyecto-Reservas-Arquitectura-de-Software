package com.farjuce.appreservas.bd.task;

import com.farjuce.appreservas.bd.employee.Employee;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "task")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int duration;

    @Column
    private int price;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Employee> employees;

}
