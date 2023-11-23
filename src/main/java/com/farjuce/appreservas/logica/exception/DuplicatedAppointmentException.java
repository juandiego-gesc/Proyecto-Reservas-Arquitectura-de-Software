package com.farjuce.appreservas.logica.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuplicatedAppointmentException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(DuplicatedAppointmentException.class);

    public DuplicatedAppointmentException() {
        super();
        LOGGER.error("Duplicated appointment exception occurred.");
    }

    public DuplicatedAppointmentException(String message) {
        super(message);
        LOGGER.error("Duplicated appointment exception occurred: {}", message);
    }
}
