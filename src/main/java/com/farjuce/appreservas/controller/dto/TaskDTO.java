package com.farjuce.appreservas.controller.dto;

import lombok.Data;

@Data
public class TaskDTO {

    private String name;

    private String description;

    private int duration;

    private int price;

}
