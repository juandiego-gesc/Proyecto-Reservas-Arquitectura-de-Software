package com.farjuce.appreservas.bd.employee;

import com.farjuce.appreservas.bd.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @Column
    private Long employee_id;

    @Column
    private String job;

    @Column
    private int  hourly_rate;

    @OneToOne(mappedBy = "employee") // Forma 1
    private User user;

}
