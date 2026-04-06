# 🏥 Hospital Management System (Spring Boot)

A full-stack **Hospital Management System** developed using **Spring Boot**, designed to simplify and manage interactions between **patients, doctors, and administrators**.

This system provides secure role-based access, appointment scheduling, doctor approval workflow, and efficient healthcare management.

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

---

### 🧑 Patient Module

* Patient registration and login
* Book appointments with available doctors
* View appointment history
* Track appointment status:

  * 🟡 Pending
  * 🟢 Approved
  * 🔴 Rejected

---

### 👨‍💼 Admin Module

* Dashboard with system analytics
* Approve / reject doctor registrations
* Manage:

  * Doctors
  * Patients
  * Appointments
* Delete users and records

---

### 📅 Appointment System

* Book appointments within doctor time slots
* Prevent duplicate bookings (±5 minutes validation)
* Real-time appointment status updates

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
 └── templates/         # Thymeleaf HTML pages
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the repository

```bash
git clone https://github.com/your-username/your-repo-name.git
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

*Add your project screenshots here*

* 🔐 Login Page
<img width="1920" height="1020" alt="image" src="https://github.com/user-attachments/assets/b48eb147-ddde-4f55-8a30-15283d78ba96" />

* 📝 Registration Page
<img width="1920" height="1020" alt="image" src="https://github.com/user-attachments/assets/9632210e-5846-4a48-953f-10af9d51ecda" />

* 👨‍💼 Admin Dashboard
<img width="1920" height="1020" alt="image" src="https://github.com/user-attachments/assets/207d687d-9d3b-46ad-a4f6-fb2fbfd48924" />

  
* 🧑‍⚕️ Doctor Dashboard
<img width="1920" height="1020" alt="image" src="https://github.com/user-attachments/assets/0fe1cd65-5d56-4862-8b96-fcc64dec30a0" />


* 🧑 Patient Dashboard
<img width="1920" height="1020" alt="image" src="https://github.com/user-attachments/assets/4ded091c-437f-4260-b0e2-2128f86cdcf3" />


* 📅 Appointment Booking
<img width="1920" height="1020" alt="image" src="https://github.com/user-attachments/assets/cc1ee61e-fe35-46d8-af75-f76ec770bc43" />


---

## 🔥 Key Highlights

* ✅ Role-based secure authentication
* ✅ Admin-controlled doctor approval system
* ✅ Smart appointment booking with conflict validation
* ✅ Clean UI with Thymeleaf templates
* ✅ Modular architecture (Controller → Service → Repository)

---

## 📌 Future Enhancements

* 📧 Email notifications for appointment updates
* 💳 Online payment integration
* 📝 Prescription management system
* 📱 QR code-based appointment system
* 📊 Advanced analytics dashboard

---

## 👨‍💻 Author

**Naveen A**
Electronics and Communication Engineering
Aspiring Java Full Stack Developer 🚀

---

## ⭐ Support

If you like this project:

⭐ Star the repository
🍴 Fork it
📢 Share it

---

## 📬 Contact

Feel free to connect for collaboration or opportunities!
