package gui;

import model.Employee;
import service.PayrollService;

import javax.swing.*;
import java.awt.*;

public class AddEmployeeForm extends JFrame {

    JTextField nameField;
    JTextField deptField;
    JTextField designationField;
    JTextField salaryField;
    JTextField phoneField;
    JTextField usernameField;

    JButton addButton;
    JButton viewButton;
    JButton salaryButton;

    public AddEmployeeForm() {

        setTitle("Employee Payroll System");

        setSize(400, 450);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new GridLayout(9, 2, 10, 10));

        // Name
        add(new JLabel("Name:"));

        nameField = new JTextField();

        add(nameField);

        // Department
        add(new JLabel("Department:"));

        deptField = new JTextField();

        add(deptField);

        // Designation
        add(new JLabel("Designation:"));

        designationField = new JTextField();

        add(designationField);

        // Salary
        add(new JLabel("Salary:"));

        salaryField = new JTextField();

        add(salaryField);

        // Phone
        add(new JLabel("Phone:"));

        phoneField = new JTextField();

        add(phoneField);

        // Username
        add(new JLabel("Username:"));

        usernameField = new JTextField();

        add(usernameField);

        // Buttons
        addButton = new JButton("Add Employee");

        viewButton = new JButton("View Employees");
        salaryButton = new JButton("Salary Calculator");

        add(addButton);

        add(viewButton);
        add(salaryButton);
        

        // Add Employee
        addButton.addActionListener(e -> {

            try {

                String name =
                        nameField.getText();

                String dept =
                        deptField.getText();

                String designation =
                        designationField.getText();

                double salary =
                        Double.parseDouble(
                                salaryField.getText()
                        );

                String phone =
                        phoneField.getText();

                String username =
                        usernameField.getText();

                Employee emp = new Employee(
                        name,
                        dept,
                        designation,
                        salary,
                        phone,
                        username
                );

                PayrollService service =
                        new PayrollService();

                service.addEmployee(emp);

                JOptionPane.showMessageDialog(
                        null,
                        "Employee Added Successfully"
                );

                // Clear fields
                nameField.setText("");

                deptField.setText("");

                designationField.setText("");

                salaryField.setText("");

                phoneField.setText("");

                usernameField.setText("");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Invalid Input"
                );
            }
        });

        // View Employees
        viewButton.addActionListener(e -> {

            new ViewEmployees();
            salaryButton.addActionListener(a -> {

    new SalaryCalculator();
});
        });

        setVisible(true);
    }
}