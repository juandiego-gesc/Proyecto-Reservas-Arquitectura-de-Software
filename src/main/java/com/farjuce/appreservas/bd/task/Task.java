package com.farjuce.appreservas.bd.task;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "task")
@Data
public class Task {

    @Id
    private Long taskId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String duration;

    @Column
    private String price;
}


