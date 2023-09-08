package com.farjuce.appreservas.controller.dto;


import lombok.Data;

@Data
public class CustomerDTO {

    private Long customer_id;
    private String name;
    private String email;
    private Long phone_number;

}
