package com.farjuce.appreservas.controller.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@RequiredArgsConstructor
@Data
public class BranchDTO {

    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private String type;
    @NonNull
    private LocalTime openingTime;
    @NonNull
    private LocalTime closureTime;

}
