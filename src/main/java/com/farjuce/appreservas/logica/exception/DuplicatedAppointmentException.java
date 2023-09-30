package com.farjuce.appreservas.logica.exception;

public class DuplicatedAppointmentException extends RuntimeException{

    public DuplicatedAppointmentException() {
        super();
    }

    public DuplicatedAppointmentException(String message) {
        super(message);
    }
}
