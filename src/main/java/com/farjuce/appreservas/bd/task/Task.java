package com.farjuce.appreservas.bd.task;

import com.farjuce.appreservas.bd.appointment.Appointment;
import com.farjuce.appreservas.bd.employee.Employee;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "task")
@Data
public class Task {

    // Auto generate ID

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long task_id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int duration;

    @Column
    private int price;

    /*@ManyToMany(mappedBy = "task")
    private Set<Employee> employees;*/

    @OneToMany (mappedBy= "task")
    private Set<Appointment> appointments;
}


