package com.farjuce.appreservas.bd.employee;

import com.farjuce.appreservas.bd.task.Task;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonManagedReference
    private Task task;

}
