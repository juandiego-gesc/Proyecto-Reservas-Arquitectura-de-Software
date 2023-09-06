package com.farjuce.appreservas.bd.appointment;

import com.farjuce.appreservas.bd.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
}
