package com.farjuce.appreservas.controller.dto;


import lombok.Data;

@Data
public class CustomerDTO {

    private Long user_id;

    private String name;

    private String email;

    private int  phone_number;

}
