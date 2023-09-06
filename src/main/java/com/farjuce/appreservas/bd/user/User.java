package com.farjuce.appreservas.bd.user;

import com.farjuce.appreservas.bd.employee.Employee;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @Column
    private int user_id;

    @Column
    private String name;

    @Column
    private String  email;

    @Column
    private int  phone_number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
