package com.farjuce.appreservas.bd.business;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "business")
@Data
public class Business {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long business_id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String type;

    @Column
    private LocalTime opening_time;

    @Column
    private LocalTime closure_time;
}
