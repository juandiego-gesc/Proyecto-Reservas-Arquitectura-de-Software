package com.farjuce.appreservas.bd.employee;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.task.Task;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    private Long employee_id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(
        name = "employee_task",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> task;

    @OneToMany (mappedBy= "employee")
    private Set<Appointment> appointment;

}
