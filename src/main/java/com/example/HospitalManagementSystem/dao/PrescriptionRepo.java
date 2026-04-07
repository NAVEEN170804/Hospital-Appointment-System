package com.example.HospitalManagementSystem.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.HospitalManagementSystem.entity.Prescription;

public interface PrescriptionRepo extends JpaRepository<Prescription, Integer> {

    // Get a prescription by appointment id
    Optional<Prescription> findByAppointmentId(int appointmentId);

    // Get all prescriptions for a specific patient (via appointment → patient)
    @Query("SELECT p FROM Prescription p WHERE p.appointment.patient.id = :patientId")
    List<Prescription> findByPatientId(@Param("patientId") int patientId);

    // Get all prescriptions written by a specific doctor (via appointment → doctor)
    @Query("SELECT p FROM Prescription p WHERE p.appointment.doctor.id = :doctorId")
    List<Prescription> findByDoctorId(@Param("doctorId") int doctorId);
}