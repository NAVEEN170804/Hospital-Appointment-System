package com.example.HospitalManagementSystem.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.HospitalManagementSystem.dao.DoctorRepo;
import com.example.HospitalManagementSystem.entity.*;
import com.example.HospitalManagementSystem.service.*;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired DoctorRepo doctorRepo;
    @Autowired PatientService patientService;
    @Autowired AppointmentService appointmentService;
    @Autowired DoctorTimeSlotService slotService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        Patient patient = patientService.getPatientByEmail(principal.getName()).orElse(null);

        if (patient == null) {
            model.addAttribute("error", "Patient profile not found.");
            model.addAttribute("appointments", java.util.Collections.emptyList());
            model.addAttribute("totalCount", 0);
            model.addAttribute("pendingCount", 0);
            model.addAttribute("approvedCount", 0);
        } else {
            java.util.List<Appointment> appts =
                    appointmentService.getPatientAppointments(patient.getId());
            model.addAttribute("patient", patient);
            model.addAttribute("appointments", appts);
            model.addAttribute("totalCount", appts.size());
            model.addAttribute("pendingCount",
                    appts.stream().filter(a -> a.getStatus() == AppointmentStatus.PENDING).count());
            model.addAttribute("approvedCount",
                    appts.stream().filter(a -> a.getStatus() == AppointmentStatus.APPROVED).count());
        }
        return "patient-dashboard";
    }

    @GetMapping("/book")
    public String bookPage(@RequestParam(required = false) Integer doctorId, Model model) {
        // Only show approved doctors
        java.util.List<Doctor> approvedDoctors = doctorRepo.findAll()
                .stream().filter(Doctor::isApproved).toList();
        model.addAttribute("doctors", approvedDoctors);

        if (doctorId != null) {
            model.addAttribute("selectedDoctorId", doctorId);
            model.addAttribute("slots", slotService.getSlotsByDoctor(doctorId));
        }
        return "book-appointment";
    }

    @PostMapping("/book")
    public String book(@RequestParam int doctorId,
                       @RequestParam String appointmentDate,
                       @RequestParam(required = false) String notes,
                       Principal principal,
                       Model model) {
        try {
            Patient patient = patientService.getPatientByEmail(principal.getName())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));

            Appointment a = new Appointment();
            a.setDoctor(doctorRepo.findById(doctorId).orElseThrow());
            a.setPatient(patient);
            a.setAppointmentDate(LocalDateTime.parse(appointmentDate));
            a.setNotes(notes);
            appointmentService.bookAppointment(a);
            return "redirect:/patient/dashboard";

        } catch (RuntimeException e) {
            java.util.List<Doctor> approvedDoctors = doctorRepo.findAll()
                    .stream().filter(Doctor::isApproved).toList();
            model.addAttribute("error", e.getMessage());
            model.addAttribute("doctors", approvedDoctors);
            model.addAttribute("selectedDoctorId", doctorId);
            model.addAttribute("slots", slotService.getSlotsByDoctor(doctorId));
            return "book-appointment";
        }
    }
}