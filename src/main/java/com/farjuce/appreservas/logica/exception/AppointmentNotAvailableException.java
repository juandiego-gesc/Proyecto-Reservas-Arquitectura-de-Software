package com.farjuce.appreservas.logica.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppointmentNotAvailableException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentNotAvailableException.class);

    public AppointmentNotAvailableException() {
        super();
        LOGGER.error("Appointment not available");
    }

    public AppointmentNotAvailableException(String message) {
        super(message);
        LOGGER.error("Appointment not available: {}", message);
    }
}
