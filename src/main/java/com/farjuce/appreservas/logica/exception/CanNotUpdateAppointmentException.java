package com.farjuce.appreservas.logica.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CanNotUpdateAppointmentException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(CanNotUpdateAppointmentException.class);

    public CanNotUpdateAppointmentException() {
        super();
        LOGGER.error("The Appointment can't be updated");
    }

    public CanNotUpdateAppointmentException(String message) {
        super(message);
        LOGGER.error("The Appointment can't be updated: {}", message);
    }
}
