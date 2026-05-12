package gui;

import database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDashboard extends JFrame {

    public UserDashboard(String username) {

        setTitle("Employee Dashboard");

        setSize(500, 300);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(5, 1));

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM employees WHERE username=?";

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setString(1, username);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                add(new JLabel(
                        "Name: " +
                                rs.getString("name")
                ));

                add(new JLabel(
                        "Department: " +
                                rs.getString("department")
                ));

                add(new JLabel(
                        "Designation: " +
                                rs.getString("designation")
                ));

                add(new JLabel(
                        "Salary: " +
                                rs.getDouble("salary")
                ));

                add(new JLabel(
                        "Phone: " +
                                rs.getString("phone")
                ));
            }

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }

        setVisible(true);
    }
}