package com.farjuce.appreservas.bd.employee;

import com.farjuce.appreservas.bd.brach.Branch;
import com.farjuce.appreservas.bd.task.Task;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private Task task;

    @ManyToOne
    @JoinColumn(name = "branchId") // Nombre de la columna que relaciona Employee con Branch
    private Branch branch;

}
