package model;

public class Employee {

    private int empId;

    private String name;

    private String department;

    private String designation;

    private double salary;

    private String phone;

    private String username;

    // Constructor
    public Employee(
            String name,
            String department,
            String designation,
            double salary,
            String phone,
            String username
    ) {

        this.name = name;

        this.department = department;

        this.designation = designation;

        this.salary = salary;

        this.phone = phone;

        this.username = username;
    }

    // Getters
    public String getName() {

        return name;
    }

    public String getDepartment() {

        return department;
    }

    public String getDesignation() {

        return designation;
    }

    public double getSalary() {

        return salary;
    }

    public String getPhone() {

        return phone;
    }

    public String getUsername() {

        return username;
    }
}