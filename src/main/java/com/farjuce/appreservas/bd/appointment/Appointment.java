package com.farjuce.appreservas.bd.appointment;

import com.farjuce.appreservas.bd.customer.Customer;
import com.farjuce.appreservas.bd.employee.Employee;
import com.farjuce.appreservas.bd.task.Task;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
@Data
@Builder
public class Appointment {

    @Id
    private Long appointment_id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn (name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn (name = "task_id")
    private Task task;

    @Column
    private String date;

    @Column
    private String start_time;

    @Column
    private String end_time;

    @Column
    private String state;

}
