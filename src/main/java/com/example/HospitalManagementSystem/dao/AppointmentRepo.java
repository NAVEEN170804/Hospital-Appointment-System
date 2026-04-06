package com.example.HospitalManagementSystem.dao;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.HospitalManagementSystem.entity.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByDoctorId(int id);
    List<Appointment> findByPatientId(int id);

    // Check if a slot is already booked within ±5 minutes
    List<Appointment> findByDoctorIdAndAppointmentDateBetween(
        int doctorId, LocalDateTime from, LocalDateTime to);
}