package com.farjuce.appreservas.bd.user;

import com.farjuce.appreservas.bd.customer.Customer;
import com.farjuce.appreservas.bd.employee.Employee;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@Inheritance(strategy = InheritanceType.JOINED) // Hace parte de la forma 2
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column
    private String name;

    @Column
    private String  email;

    @Column
    private int  phone_number;

    // Todo: Al parecer existen dos formas de hacer la relación 1 a 1, verificar cual es la mejor

    @OneToOne(cascade = CascadeType.ALL) // Forma 1
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne(mappedBy = "user") // Forma 2
    private Customer customer;

}
