package com.farjuce.appreservas.bd.brach;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "branch")
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branch_id;

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
