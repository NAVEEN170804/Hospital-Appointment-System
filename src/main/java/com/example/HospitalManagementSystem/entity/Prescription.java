package com.example.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Each prescription is tied to one appointment
    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @Column(columnDefinition = "TEXT")
    private String medicines;       // e.g. "Paracetamol 500mg - 1 tablet twice daily"

    @Column(columnDefinition = "TEXT")
    private String instructions;    // general instructions / diet / rest

    @Column(columnDefinition = "TEXT")
    private String diagnosis;       // doctor's diagnosis note

    private LocalDate issuedDate;

    public Prescription() {}

    public Prescription(Appointment appointment, String medicines,
                        String instructions, String diagnosis, LocalDate issuedDate) {
        this.appointment   = appointment;
        this.medicines     = medicines;
        this.instructions  = instructions;
        this.diagnosis     = diagnosis;
        this.issuedDate    = issuedDate;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public String getMedicines() {
		return medicines;
	}

	public void setMedicines(String medicines) {
		this.medicines = medicines;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public LocalDate getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(LocalDate issuedDate) {
		this.issuedDate = issuedDate;
	}

  

}