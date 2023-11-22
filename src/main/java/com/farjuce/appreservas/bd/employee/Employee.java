package com.farjuce.appreservas.bd.employee;

import com.farjuce.appreservas.bd.brach.Branch;
import com.farjuce.appreservas.bd.task.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "taskId")
    @JsonIgnore
    private Task task;

    @ManyToOne
    @JoinColumn(name = "branchId")
    @JsonIgnore
    private Branch branch;
}
