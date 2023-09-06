package com.farjuce.appreservas.bd.service;

import com.farjuce.appreservas.bd.customer.Customer;
import com.farjuce.appreservas.bd.employee.Employee;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "service")
@Data
public class Service {

    @Id
    private Long Service_id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String duration;

    @Column
    private String price;
}


