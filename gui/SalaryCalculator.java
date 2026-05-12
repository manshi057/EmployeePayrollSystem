package gui;

import database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SalaryCalculator extends JFrame {

    JTextField empIdField;

    JTextField bonusField;

    JTextField deductionField;

    JButton calculateButton;

    JLabel resultLabel;

    public SalaryCalculator() {

        setTitle("Salary Calculator");

        setSize(400, 300);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(5, 2, 10, 10));

        // Employee ID
        add(new JLabel("Employee ID:"));

        empIdField = new JTextField();

        add(empIdField);

        // Bonus
        add(new JLabel("Bonus:"));

        bonusField = new JTextField();

        add(bonusField);

        // Deduction
        add(new JLabel("Deduction:"));

        deductionField = new JTextField();

        add(deductionField);

        // Button
        calculateButton =
                new JButton("Calculate Salary");

        add(new JLabel(""));

        add(calculateButton);

        // Result
        resultLabel =
                new JLabel("");

        add(resultLabel);

        // Action
        calculateButton.addActionListener(e -> {

            calculateSalary();
        });

        setVisible(true);
    }

    private void calculateSalary() {

        try {

            int empId =
                    Integer.parseInt(
                            empIdField.getText()
                    );

            double bonus =
                    Double.parseDouble(
                            bonusField.getText()
                    );

            double deduction =
                    Double.parseDouble(
                            deductionField.getText()
                    );

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT salary FROM employees WHERE emp_id=?";

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setInt(1, empId);

            ResultSet rs =
                    pst.executeQuery();

            if (rs.next()) {

                double basicSalary =
                        rs.getDouble("salary");

                double netSalary =
                        basicSalary + bonus - deduction;

                resultLabel.setText(
                        "Net Salary: " + netSalary
                );

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Employee Not Found"
                );
            }

            con.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Invalid Input"
            );
        }
    }
}