package com.farjuce.appreservas.bd.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(value = "SELECT * FROM appointment where customer_id = :customer_id", nativeQuery = true)
    List<Appointment> findByCustomerId(@Param("customer_id") Long customerId);

    @Query(value = "SELECT emp.employee_id, emp.name " +
            "FROM employee emp , branch as br " +
            "WHERE emp.task_id = :taskId " +
            "AND emp.employee_id NOT IN ( " +
            "    SELECT app.employee_id " +
            "    FROM appointment app " +
            "    WHERE app.date = :date " +
            "    AND (" +
            "        (app.start_time <= :startTime AND app.end_time > :startTime) " +
            "        OR " +
            "        (app.start_time < :endTime AND app.end_time >= :endTime) " +
            "        OR " +
            "        (app.start_time >= :startTime AND app.end_time <= :endTime) " +
            "    ) " +
            "    AND app.task_id = :taskId " +
            ") AND br.branch_id = 1 " +
            "  AND (br.opening_time <= :startTime AND br.closure_time >= :endTime)", nativeQuery = true)
    List<Object[]> findAvailableEmployees(
            @Param("taskId") Long taskId,
            @Param("date") String date,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime);
}
