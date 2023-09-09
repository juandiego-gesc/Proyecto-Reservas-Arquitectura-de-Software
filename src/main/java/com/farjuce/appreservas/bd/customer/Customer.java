package com.farjuce.appreservas.bd.customer;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

import javax.persistence.*;

import com.farjuce.appreservas.bd.appointment.Appointment;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long costumer_id;

    @Column
    private String name;  

    @Column
    private String email;  

    @Column
    private long phone_number; 

    @OneToMany (mappedBy= "customer")
    private Set<Appointment> appointments;
}
