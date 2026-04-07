# 🏥 Hospital Management System (Spring Boot)

A full-stack **Hospital Management System** developed using **Spring Boot**, designed to simplify and manage interactions between **patients, doctors, and administrators**.

This system provides secure role-based access, appointment scheduling, doctor approval workflow, **prescription management**, and efficient healthcare operations.

---

## 🚀 Features

### 🔐 Authentication & Authorization

* Secure login and registration system
* Role-based access control:

  * 👨‍💼 Admin
  * 🧑‍⚕️ Doctor
  * 🧑 Patient
* Password encryption using BCrypt
* Spring Security integration

---

### 🧑‍⚕️ Doctor Module

* Doctor registration with **admin approval required**
* Cannot login until approved by admin
* Manage availability using time slots
* View patient appointments
* Approve or reject appointment requests
* 📝 **Create and manage prescriptions for patients**

---

### 🧑 Patient Module

* Patient registration and login
* Book appointments with available doctors
* View appointment history
* Track appointment status:

  * 🟡 Pending
  * 🟢 Approved
  * 🔴 Rejected
* 📝 **View prescriptions provided by doctors**

---

### 👨‍💼 Admin Module

* Dashboard with system analytics
* Approve / reject doctor registrations
* Manage:

  * Doctors
  * Patients
  * Appointments
  * Prescriptions
* Delete users and records

---

### 📅 Appointment System

* Book appointments within doctor time slots
* Prevent duplicate bookings (±5 minutes validation)
* Real-time appointment status updates

---

### 📝 Prescription Module (NEW 🚀)

* Doctors can create prescriptions after consultation
* Add details like:

  * Diagnosis
  * Medicines
  * Dosage instructions
  * Notes
* Patients can view their prescription history
* Secure access (only assigned patient & doctor)

---

## 🏗️ Tech Stack

| Layer      | Technology            |
| ---------- | --------------------- |
| Backend    | Java, Spring Boot     |
| Security   | Spring Security       |
| Database   | MySQL (JPA/Hibernate) |
| Frontend   | Thymeleaf, HTML, CSS  |
| Build Tool | Maven                 |

---

## 📂 Project Structure

```
src/
 ├── controller/        # Handles HTTP requests
 ├── service/           # Business logic layer
 ├── dao/               # Repository layer (JPA)
 ├── entity/            # Database entities
 ├── security/          # Security configuration
 ├── templates/         # Thymeleaf HTML pages
 └── prescription/      # Prescription module
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the repository

```bash
git clone https://github.com/NAVEEN170804/Hospital-Appointment-System.git
```

---

### 2️⃣ Open in IDE

* IntelliJ IDEA / Eclipse / Spring Tool Suite

---

### 3️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 4️⃣ Run the Application

```bash
mvn spring-boot:run
```

---

### 5️⃣ Open in Browser

```
http://localhost:8080/login
```

---

## 🔐 Default Admin Credentials

```
Email: admin@medcare.com
Password: admin123
```

👉 Automatically created using DataSeeder

---

## 📸 Screenshots

* 🔐 Login Page

  
* 📝 Registration Page
* 👨‍💼 Admin Dashboard
* 🧑‍⚕️ Doctor Dashboard
* 🧑 Patient Dashboard
* 📅 Appointment Booking
* 📝 Prescription Page (NEW)

---

## 🔥 Key Highlights

* ✅ Role-based secure authentication
* ✅ Admin-controlled doctor approval system
* ✅ Smart appointment booking with conflict validation
* ✅ 📝 Prescription management system
* ✅ Clean UI with Thymeleaf templates
* ✅ Modular architecture (Controller → Service → Repository)

---

## 📌 Future Enhancements

* 📧 Email notifications for appointment updates
* 💳 Online payment integration
* 📱 QR code-based appointment system
* 📊 Advanced analytics dashboard

---

## 👨‍💻 Author

* **Naveen A**
* 📧 Email: [naveena170804@gmail.com](mailto:naveena170804@gmail.com)
* 🐙 GitHub: [NAVEEN170804](https://github.com/NAVEEN170804)
* 🌐 Portfolio: [View Portfolio](https://naveen170804.github.io/Portfolio)

---
