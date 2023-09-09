package com.farjuce.appreservas.bd.appointment;

import com.farjuce.appreservas.bd.customer.Customer;
import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.bd.task.Task;

import lombok.Data;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
@Data
public class Appointment{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long appointment_id;

    @Column
    private LocalDate date;

    @Column
    private String start_time;

    @Column
    private String end_time;

    @Column
    private String state;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn (name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn (name = "task_id")
    private Task task;
}
