package com.farjuce.appreservas.controller.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EmployeeDTO {

    @NonNull
    private String name;
    @NonNull
    private Long taskId;
    @NonNull
    private Long branchId;

}
