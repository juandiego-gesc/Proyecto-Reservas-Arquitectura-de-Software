package com.farjuce.appreservas.controller.dto;

import lombok.Data;

@Data
public class TaskDTO {

    private Long taskId;

    private String name;

    private String description;

    private String duration;

    private String price;

}
