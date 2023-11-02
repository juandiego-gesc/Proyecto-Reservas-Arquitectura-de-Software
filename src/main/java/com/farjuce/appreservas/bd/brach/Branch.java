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
    private Long branchId;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String type;

    @Column
    private LocalTime openingTime;

    @Column
    private LocalTime closureTime;
}
