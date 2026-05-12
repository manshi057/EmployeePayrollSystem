package service;

import database.DBConnection;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PayrollService {

    public void addEmployee(Employee emp) {

        String query =
                "INSERT INTO employees(name, department, designation, salary, phone, username) VALUES (?, ?, ?, ?, ?, ?)";

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setString(1, emp.getName());

            pst.setString(2, emp.getDepartment());

            pst.setString(3, emp.getDesignation());

            pst.setDouble(4, emp.getSalary());

            pst.setString(5, emp.getPhone());

            pst.setString(6, emp.getUsername());

            pst.executeUpdate();

            System.out.println(
                    "Employee Added Successfully"
            );

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}