package com.example.HospitalManagementSystem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.HospitalManagementSystem.dao.PrescriptionRepo;
import com.example.HospitalManagementSystem.entity.Prescription;

@Service
public class PrescriptionService {

    @Autowired
    PrescriptionRepo repo;

    public void save(Prescription p) 
    { 
     	repo.save(p); 
    	}

    public Optional<Prescription> getByAppointmentId(int appointmentId) {
        return repo.findByAppointmentId(appointmentId);
    }

    public List<Prescription> getByPatientId(int patientId) {
        return repo.findByPatientId(patientId);
    }

    public List<Prescription> getByDoctorId(int doctorId) {
        return repo.findByDoctorId(doctorId);
    }

    public Optional<Prescription> getById(int id) {
        return repo.findById(id);
    }

    public void delete(int id) { 
    	     repo.deleteById(id); 
    }
}