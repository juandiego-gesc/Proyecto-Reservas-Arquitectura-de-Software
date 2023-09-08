package com.farjuce.appreservas.bd.customer;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

import javax.persistence.*;

import com.farjuce.appreservas.bd.appointment.Appointment;

@Entity
@Table(name = "customer")

@Data
@Builder
public class Customer {
    
    @Id
    private Long customer_id;

    @Column
    private String name;  

    @Column
    private String email;  

    @Column
    private long phone_number; 

    @OneToMany (mappedBy= "customer")
    private Set<Appointment> appointments;
}
