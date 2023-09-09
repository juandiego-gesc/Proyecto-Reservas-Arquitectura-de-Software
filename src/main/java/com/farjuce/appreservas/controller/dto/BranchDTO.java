package com.farjuce.appreservas.controller.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class BranchDTO {

    private Long branch_id;
    private String name;
    private String address;
    private String type;
    private LocalTime opening_time;
    private LocalTime closure_time;

}
