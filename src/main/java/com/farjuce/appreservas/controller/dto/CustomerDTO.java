package com.farjuce.appreservas.controller.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerDTO {

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private Long phone_number;

}
