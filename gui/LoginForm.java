package gui;

import database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForm extends JFrame {

    JComboBox<String> roleBox;

    JTextField usernameField;

    JPasswordField passwordField;

    JButton loginButton;

    public LoginForm() {

        setTitle("Payroll System Login");

        setSize(400, 250);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(4, 2, 10, 10));

        // Role
        add(new JLabel("Role:"));

        roleBox = new JComboBox<>();

        roleBox.addItem("Admin");

        roleBox.addItem("User");

        add(roleBox);

        // Username
        add(new JLabel("Username:"));

        usernameField = new JTextField();

        add(usernameField);

        // Password
        add(new JLabel("Password:"));

        passwordField = new JPasswordField();

        add(passwordField);

        // Button
        loginButton = new JButton("Login");

        add(new JLabel(""));

        add(loginButton);

        // Action
        loginButton.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {

        String role =
                roleBox.getSelectedItem().toString();

        String username =
                usernameField.getText();

        String password =
                String.valueOf(
                        passwordField.getPassword()
                );

        try {

            Connection con =
                    DBConnection.getConnection();

            String query = "";

            // Admin Login
            if (role.equals("Admin")) {

                query =
                        "SELECT * FROM admin WHERE username=? AND password=?";

            } else {

                query =
                        "SELECT * FROM users WHERE username=? AND password=?";
            }

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setString(1, username);

            pst.setString(2, password);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(
                        null,
                        role + " Login Successful"
                );

                // Admin Access
                if (role.equals("Admin")) {

                    new AddEmployeeForm();

                } else {

                    new UserDashboard(username);
                }

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Invalid Credentials"
                );
            }

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}