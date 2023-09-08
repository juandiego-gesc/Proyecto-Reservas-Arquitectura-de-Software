package com.farjuce.appreservas.controller.dto;

import com.farjuce.appreservas.bd.task.Task;
import lombok.Data;

import java.util.Set;

@Data
public class EmployeeDTO {

    private Long employee_id;
    private String name;
    private Set<Long> task_ids;

}
