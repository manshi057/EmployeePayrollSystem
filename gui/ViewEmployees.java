package gui;

import database.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewEmployees extends JFrame {

    JTable table;

    DefaultTableModel model;

    JButton deleteButton;

    JButton updateButton;

    public ViewEmployees() {

        setTitle("Employee Records");

        setSize(700, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
                "ID",
                "Name",
                "Department",
                "Designation",
                "Salary",
                "Phone"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        loadEmployeeData();

        JScrollPane scrollPane =
                new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        deleteButton =
                new JButton("Delete Employee");

        updateButton =
                new JButton("Update Employee");

        JPanel panel = new JPanel();

        panel.add(updateButton);

        panel.add(deleteButton);

        add(panel, BorderLayout.SOUTH);

        // Delete Button Action
        deleteButton.addActionListener(e -> {

            int selectedRow =
                    table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please select employee"
                );

                return;
            }

            int empId =
                    (int) model.getValueAt(
                            selectedRow,
                            0
                    );

            deleteEmployee(empId);

            model.removeRow(selectedRow);

            JOptionPane.showMessageDialog(
                    null,
                    "Employee Deleted Successfully"
            );
        });

        // Update Button Action
        updateButton.addActionListener(e -> {

            int selectedRow =
                    table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please select employee"
                );

                return;
            }

            int empId =
                    (int) model.getValueAt(selectedRow, 0);

            String currentName =
                    (String) model.getValueAt(selectedRow, 1);

            String currentDept =
                    (String) model.getValueAt(selectedRow, 2);

            String currentDesignation =
                    (String) model.getValueAt(selectedRow, 3);

            double currentSalary =
                    (double) model.getValueAt(selectedRow, 4);

            String currentPhone =
                    (String) model.getValueAt(selectedRow, 5);

            String newName =
                    JOptionPane.showInputDialog(
                            "Enter Name",
                            currentName
                    );

            String newDept =
                    JOptionPane.showInputDialog(
                            "Enter Department",
                            currentDept
                    );

            String newDesignation =
                    JOptionPane.showInputDialog(
                            "Enter Designation",
                            currentDesignation
                    );

            String salaryInput =
                    JOptionPane.showInputDialog(
                            "Enter Salary",
                            currentSalary
                    );

            String newPhone =
                    JOptionPane.showInputDialog(
                            "Enter Phone",
                            currentPhone
                    );

            double newSalary =
                    Double.parseDouble(salaryInput);

            updateEmployee(
                    empId,
                    newName,
                    newDept,
                    newDesignation,
                    newSalary,
                    newPhone
            );

            // Update JTable
            model.setValueAt(newName, selectedRow, 1);
            model.setValueAt(newDept, selectedRow, 2);
            model.setValueAt(newDesignation, selectedRow, 3);
            model.setValueAt(newSalary, selectedRow, 4);
            model.setValueAt(newPhone, selectedRow, 5);

            JOptionPane.showMessageDialog(
                    null,
                    "Employee Updated Successfully"
            );
        });

        setVisible(true);
    }

    // Load Employee Data
    private void loadEmployeeData() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "SELECT * FROM employees";

            PreparedStatement pst =
                    con.prepareStatement(query);

            ResultSet rs =
                    pst.executeQuery();

            while (rs.next()) {

                Object[] row = {

                        rs.getInt("emp_id"),

                        rs.getString("name"),

                        rs.getString("department"),

                        rs.getString("designation"),

                        rs.getDouble("salary"),

                        rs.getString("phone")
                };

                model.addRow(row);
            }

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    // Delete Employee
    private void deleteEmployee(int empId) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "DELETE FROM employees WHERE emp_id=?";

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setInt(1, empId);

            pst.executeUpdate();

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    // Update Employee
    private void updateEmployee(
            int empId,
            String name,
            String department,
            String designation,
            double salary,
            String phone
    ) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String query =
                    "UPDATE employees SET name=?, department=?, designation=?, salary=?, phone=? WHERE emp_id=?";

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setString(1, name);

            pst.setString(2, department);

            pst.setString(3, designation);

            pst.setDouble(4, salary);

            pst.setString(5, phone);

            pst.setInt(6, empId);

            pst.executeUpdate();

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}