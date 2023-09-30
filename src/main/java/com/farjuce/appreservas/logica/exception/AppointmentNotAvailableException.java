package com.farjuce.appreservas.logica.exception;

public class AppointmentNotAvailableException extends RuntimeException{

    public AppointmentNotAvailableException() {
        super();
    }

    public AppointmentNotAvailableException(String message) {
        super(message);
    }
}
