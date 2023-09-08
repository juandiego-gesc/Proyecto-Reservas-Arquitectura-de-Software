package com.farjuce.appreservas.bd.customer;

import com.farjuce.appreservas.bd.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    private Long customer_id;

    @OneToOne
    @JoinColumn(name = "customer_id") // Forma 2
    private User user;
}
