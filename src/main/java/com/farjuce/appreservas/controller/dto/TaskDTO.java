package com.farjuce.appreservas.controller.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TaskDTO {

    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private int duration;
    @NonNull
    private int price;

}
