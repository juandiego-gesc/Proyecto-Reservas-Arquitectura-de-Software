package com.farjuce.appreservas.bd.appointment;

import com.farjuce.appreservas.bd.customer.Customer;
import com.farjuce.appreservas.bd.employee.Employee;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
@Data
public class Appointment {

    @Id
    private Long appointment_id;

    @Column
    private String date;

    @Column
    private String start_time;

    @Column
    private String end_time;

    @Column
    private String status;

    @Column
    @OneToOne
    Employee employee;

    @Column
    @OneToOne
    Customer customer;
}
