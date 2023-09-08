package com.farjuce.appreservas.controller.dto;

import com.farjuce.appreservas.bd.user.User;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
public class EmployeeDTO {

    private Long employee_id;

    private String job;

    private int  hourly_rate;

}
