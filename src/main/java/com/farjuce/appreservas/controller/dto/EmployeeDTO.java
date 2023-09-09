package com.farjuce.appreservas.controller.dto;

import com.farjuce.appreservas.bd.task.Task;
import lombok.Data;

import java.util.Set;

@Data
public class EmployeeDTO {

    private String name;
    private Long task_id;

}
