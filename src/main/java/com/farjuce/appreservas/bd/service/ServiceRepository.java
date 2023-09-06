package com.farjuce.appreservas.bd.service;

import com.farjuce.appreservas.bd.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long>{
}
