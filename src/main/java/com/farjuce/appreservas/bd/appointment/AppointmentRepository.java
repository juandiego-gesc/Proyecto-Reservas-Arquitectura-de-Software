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
            "        (TIME(app.start_time) <= TIME(:startTime) AND TIME(app.end_time) > TIME(:startTime)) " +
            "        OR " +
            "        (TIME(app.start_time) < TIME(:endTime) AND TIME(app.end_time) >= TIME(:endTime)) " +
            "        OR " +
            "        (TIME(app.start_time) >= TIME(:startTime) AND TIME(app.end_time) <= TIME(:endTime)) " +
            "    ) " +
            "    AND app.task_id = :taskId " +
            ") AND br.branch_id = 1 " +
            "  AND (TIME(br.opening_time) <= TIME(:startTime) AND TIME(br.closure_time) > TIME(:endTime))", nativeQuery = true)
    List<Object[]> findAvailableEmployees(
            @Param("taskId") Long taskId,
            @Param("date") String date,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime);
}
