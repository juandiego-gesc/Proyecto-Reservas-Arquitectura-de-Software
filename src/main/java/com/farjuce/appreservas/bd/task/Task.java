package com.farjuce.appreservas.bd.task;

import com.farjuce.appreservas.bd.employee.Employee;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "task")
@Data
public class Task {

    @Id
    private Long taskId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String duration;

    @Column
    private String price;

    @ManyToMany(mappedBy = "task")
    private Set<Employee> employees;

}


