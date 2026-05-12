# Employee Payroll Management System

A Java-based Employee Payroll Management System developed using Java Swing, JDBC, and MySQL.  
This project helps manage employee records, authentication, and salary-related operations through a simple desktop GUI application.

The system includes separate Admin and User roles with different access permissions, making it closer to a real-world payroll management application.

---

# Features

## Admin Features
- Admin Login Authentication
- Add Employee
- View Employee Records
- Update Employee Details
- Delete Employee
- Salary Calculation Module
- Employee Management using JTable

## User Features
- User Login Authentication
- View Personal Employee Details
- View Salary Information

---

# Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- OOP Concepts
- SQL

---

# Project Structure

EmployeePayrollSystem  
│  
├── src  
│   ├── database  
│   ├── gui  
│   ├── model  
│   ├── service  
│  
└── lib  

---

# Database Tables

## admin
Stores admin login credentials.

## users
Stores employee user login credentials.

## employees
Stores employee details such as:
- Name
- Department
- Designation
- Salary
- Phone Number
- Username

---

# Modules Implemented

- Login System
- Role-Based Authentication
- Employee CRUD Operations
- Salary Calculator
- User Dashboard
- Database Connectivity

---

# How to Run the Project

1. Clone the repository
2. Open project in VS Code or IntelliJ
3. Add MySQL Connector JAR file
4. Create MySQL database

