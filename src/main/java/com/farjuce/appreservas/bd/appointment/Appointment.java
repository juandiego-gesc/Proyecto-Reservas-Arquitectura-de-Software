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
    private Long appointmentId;

    @Column
    private String date;

    @Column
    private String startTime;

    @Column
    private String endTime;

    @Column
    private String status;

    @Column
    @OneToOne
    Employee employee;

    @Column
    @OneToOne
    Customer customer;
}
