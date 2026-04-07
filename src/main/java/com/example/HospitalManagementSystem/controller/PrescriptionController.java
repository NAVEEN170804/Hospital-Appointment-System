package com.example.HospitalManagementSystem.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.HospitalManagementSystem.entity.*;
import com.example.HospitalManagementSystem.service.*;

@Controller
public class PrescriptionController {

    @Autowired PrescriptionService prescriptionService;
    @Autowired AppointmentService  appointmentService;
    @Autowired DoctorService       doctorService;
    @Autowired PatientService      patientService;

    // ─────────────────────────────────────────────────────────────────────────
    // DOCTOR: show form to add / edit prescription for an appointment
    // ─────────────────────────────────────────────────────────────────────────
    @GetMapping("/doctor/prescription/add/{appointmentId}")
    public String addPrescriptionForm(@PathVariable int appointmentId,
                                      Model model,
                                      Principal principal) {

        Doctor doctor = doctorService.getDoctorByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appointment = appointmentService.getAllAppointments()
                .stream()
                .filter(a -> a.getId() == appointmentId
                          && a.getDoctor().getId() == doctor.getId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Appointment not found or unauthorized"));

        // Pre-fill if prescription already exists
        prescriptionService.getByAppointmentId(appointmentId)
                .ifPresent(p -> model.addAttribute("existing", p));

        model.addAttribute("appointment", appointment);
        return "prescription-form";
    }

    // ─────────────────────────────────────────────────────────────────────────
    // DOCTOR: save / update prescription
    // ─────────────────────────────────────────────────────────────────────────
    @PostMapping("/doctor/prescription/save")
    public String savePrescription(@RequestParam int appointmentId,
                                   @RequestParam String diagnosis,
                                   @RequestParam String medicines,
                                   @RequestParam String instructions,
                                   Principal principal) {

        Doctor doctor = doctorService.getDoctorByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appointment = appointmentService.getAllAppointments()
                .stream()
                .filter(a -> a.getId() == appointmentId
                          && a.getDoctor().getId() == doctor.getId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unauthorized"));

        // Update if already exists, otherwise create new
        Prescription prescription = prescriptionService
                .getByAppointmentId(appointmentId)
                .orElse(new Prescription());

        prescription.setAppointment(appointment);
        prescription.setDiagnosis(diagnosis);
        prescription.setMedicines(medicines);
        prescription.setInstructions(instructions);
        prescription.setIssuedDate(LocalDate.now());

        prescriptionService.save(prescription);
        return "redirect:/doctor/dashboard";
    }

    // ─────────────────────────────────────────────────────────────────────────
    // DOCTOR: view all prescriptions they've written
    // ─────────────────────────────────────────────────────────────────────────
    @GetMapping("/doctor/prescriptions")
    public String doctorPrescriptions(Model model, Principal principal) {
        Doctor doctor = doctorService.getDoctorByEmail(principal.getName())
                .orElseThrow();
        List<Prescription> list = prescriptionService.getByDoctorId(doctor.getId());
        model.addAttribute("prescriptions", list);
        model.addAttribute("doctor", doctor);
        return "doctor-prescriptions";
    }

    // ─────────────────────────────────────────────────────────────────────────
    // PATIENT: view all their prescriptions
    // ─────────────────────────────────────────────────────────────────────────
    @GetMapping("/patient/prescriptions")
    public String patientPrescriptions(Model model, Principal principal) {
        Patient patient = patientService.getPatientByEmail(principal.getName())
                .orElseThrow();
        List<Prescription> list = prescriptionService.getByPatientId(patient.getId());
        model.addAttribute("prescriptions", list);
        model.addAttribute("patient", patient);
        return "patient-prescriptions";
    }

    // ─────────────────────────────────────────────────────────────────────────
    // PATIENT: view a single prescription detail
    // ─────────────────────────────────────────────────────────────────────────
    @GetMapping("/patient/prescription/{id}")
    public String viewPrescription(@PathVariable int id,
                                   Model model,
                                   Principal principal) {

        Patient patient = patientService.getPatientByEmail(principal.getName())
                .orElseThrow();

        Prescription prescription = prescriptionService.getById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        // Security: patient can only view their own
        if (prescription.getAppointment().getPatient().getId() != patient.getId()) {
            return "redirect:/patient/prescriptions";
        }

        model.addAttribute("prescription", prescription);
        return "prescription-detail";
    }
}