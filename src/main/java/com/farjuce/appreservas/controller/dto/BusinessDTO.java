package com.farjuce.appreservas.controller.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalTime;

@Data
public class BusinessDTO {

    private Long business_id;

    private String name;

    private String address;

    private String type;

    private LocalTime opening_time;

    private LocalTime closure_time;


}
