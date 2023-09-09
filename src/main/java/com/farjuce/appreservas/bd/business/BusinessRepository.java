package com.farjuce.appreservas.bd.business;

import com.farjuce.appreservas.bd.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
}
