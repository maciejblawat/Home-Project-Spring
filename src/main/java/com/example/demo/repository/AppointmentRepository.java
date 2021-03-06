package com.example.demo.repository;

import com.example.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> , PagingAndSortingRepository<Appointment, Long> , CrudRepository<Appointment, Long> {
      @Query(value = "SELECT MIN(appointment.date) FROM appointment", nativeQuery = true)
      LocalDateTime getDate();

      @Query(value = "SELECT * FROM appointment ORDER BY appointment.date ASC LIMIT 10" , nativeQuery = true)
      List<Appointment> get10EarliestAppointments();

      @Query(value = "SELECT * FROM appointment" +
              " WHERE date = (SELECT MIN(date) FROM appointment)", nativeQuery = true)
      Appointment findEarliestAppointment();

      Appointment findByAppointmentTime(LocalDateTime appointmentTime);
}
