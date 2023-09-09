package com.farjuce.appreservas.bd.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.farjuce.appreservas.bd.appointment.Appointment;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long customer_id;

    @Column
    private String name;  

    @Column
    private String email;  

    @Column
    private long phone_number; 

    @JsonIgnore
    @OneToMany (mappedBy= "customer")
    private Set<Appointment> appointments = new HashSet<>();
}
