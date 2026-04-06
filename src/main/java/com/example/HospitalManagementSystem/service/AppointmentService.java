package com.example.HospitalManagementSystem.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.HospitalManagementSystem.dao.AppointmentRepo;
import com.example.HospitalManagementSystem.dao.DoctorTimeSlotRepo;
import com.example.HospitalManagementSystem.entity.Appointment;
import com.example.HospitalManagementSystem.entity.AppointmentStatus;
import com.example.HospitalManagementSystem.entity.DoctorTimeSlot;

@Service
public class AppointmentService {

    @Autowired AppointmentRepo repo;
    @Autowired DoctorTimeSlotRepo slotRepo;

    public void bookAppointment(Appointment a) {
        LocalDateTime requested = a.getAppointmentDate();
        int doctorId = a.getDoctor().getId();

        // 1. Check the requested time falls inside a valid slot
        String day = requested.getDayOfWeek().name(); // e.g. "MONDAY"
        LocalTime requestedTime = requested.toLocalTime();

        List<DoctorTimeSlot> slots = slotRepo.findByDoctorId(doctorId);
        boolean withinSlot = slots.stream().anyMatch(s ->
            s.getDayOfWeek().equalsIgnoreCase(day) &&
            !requestedTime.isBefore(s.getStartTime()) &&
            !requestedTime.isAfter(s.getEndTime())
        );

        if (!withinSlot) {
            throw new RuntimeException("Selected time is outside the doctor's available hours.");
        }

        // 2. Check no booking within ±5 minutes of requested time
        LocalDateTime from = requested.minusMinutes(5);
        LocalDateTime to   = requested.plusMinutes(5);
        List<Appointment> conflicts = repo.findByDoctorIdAndAppointmentDateBetween(doctorId, from, to);

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("This slot is already booked. Please choose a time at least 5 minutes away.");
        }

        a.setStatus(AppointmentStatus.PENDING);
        repo.save(a);
    }

    public List<Appointment> getAllAppointments() { return repo.findAll(); }

    public List<Appointment> getDoctorAppointments(int doctorId) {
        return repo.findByDoctorId(doctorId);
    }

    public List<Appointment> getPatientAppointments(int patientId) {
        return repo.findByPatientId(patientId);
    }

    public void updateStatus(int appointmentId, AppointmentStatus status) {
        Appointment a = repo.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found: " + appointmentId));
        a.setStatus(status);
        repo.save(a);
    }

    public void deleteAppointment(int id) { repo.deleteById(id); }
}