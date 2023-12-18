package com.landing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Registration extends JFrame{
    private JTextField tfFirstName, tfLastName, tfPhoneNum, tfAddress, tfNidNum;
    private JCheckBox safetyTrainingCheckBox, operationalTrainingCheckBox, technicalTrainingCheckBox;
    private JComboBox<String> cboxEmployeetype;
    private JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
    private JTextArea tAreaReasoning;
    private JCheckBox healthcareCheckBox, financialWellnessCheckBox, vocationalTrainingCheckBox, skillImprovementTrainingCheckBox;
    private JButton btnSubmit, btnCancel;
    private JPanel regPanel;
    private JRadioButton approvedRadioButton, pendingRadioButton;


    JFrame frame = new JFrame("Registration");
    EmployeeDataForm employeeDataForm;
    public Registration() {
        // Initialize components and set up the UI...
        frame.setContentPane(regPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 450, 300);
        frame.pack();
        frame.setVisible(true);



        // Add action listener to the submit button
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    // Write data to file
                    writeDataToFile();

                    // Add data to table
                    employeeDataForm = new EmployeeDataForm();

                    JOptionPane.showMessageDialog(null, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all required fields as name suggests.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add action listener to the cancel button
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // For demonstration purposes, let's close the Registration window
                closeRegistrationWindow();
                LandingGUI landing = new LandingGUI();
            }
        });
    }

    private void closeRegistrationWindow() {
        // This method is called when the Registration window should be closed
        frame = (JFrame) SwingUtilities.getWindowAncestor(btnCancel);
        frame.dispose();
    }

    private boolean validateFields() {
        // Validate text fields
        if (isEmpty(tfFirstName) || !isAlpha(tfFirstName.getText()) || !isValidName(tfFirstName.getText())) {
            showErrorDialog("First Name should only contain alphabetic characters.");
            return false;
        }

        if (isEmpty(tfLastName) || !isAlpha(tfLastName.getText()) || !isValidName(tfLastName.getText())) {
            showErrorDialog("Last Name should only contain alphabetic characters.");
            return false;
        }

        if (isEmpty(tfPhoneNum) || !isNumeric(tfPhoneNum.getText()) || !isValidPhoneNumber(tfPhoneNum.getText())) {
            showErrorDialog("Phone Number should be a valid 11-digit number starting with zero.");
            return false;
        }

        if (isEmpty(tfAddress)) {
            showErrorDialog("Address cannot be empty.");
            return false;
        }

        if (isEmpty(tfNidNum) || !isNumeric(tfNidNum.getText())) {
            showErrorDialog("NID Number should only contain numeric characters.");
            return false;
        }

        // Validate combo box
        if (cboxEmployeetype.getSelectedIndex() == -1) {
            showErrorDialog("Please select an Employee Type.");
            return false;
        }

        // Validate radio buttons
        if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected() && !otherRadioButton.isSelected()) {
            showErrorDialog("Please select a Gender.");
            return false;
        }

        // Validate text area
        if (tAreaReasoning.getText().trim().isEmpty()) {
            showErrorDialog("Reasoning cannot be empty.");
            return false;
        }

        // Validate checkboxes
        if (!healthcareCheckBox.isSelected() && !financialWellnessCheckBox.isSelected() && !vocationalTrainingCheckBox.isSelected() && !skillImprovementTrainingCheckBox.isSelected()) {
            showErrorDialog("Please select at least one Training Program.");
            return false;
        }

        // Validate checkboxes
        if (!safetyTrainingCheckBox.isSelected() && !operationalTrainingCheckBox.isSelected() && !technicalTrainingCheckBox.isSelected()) {
            showErrorDialog("Please select at least one Training Type.");
            return false;
        }

        return true;
    }

    private void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }


    private boolean isAlpha(String input) {
        return input.matches("^[a-zA-Z]+$");
    }

    private boolean isNumeric(String input) {
        return input.matches("^[0-9]+$");
    }


    private boolean isEmpty(JTextField textField) {
        return textField.getText().trim().isEmpty();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^01[0-9]{9}$");
    }
    private boolean isValidName(String name) {
        return name.replaceAll("[^a-zA-Z]", "").length() >= 5;
    }



    private void writeDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employee_data.txt", true))) {
            // Append the data to the text file
            writer.write("-".repeat(50)+"\n");
            writer.write("First Name: " + tfFirstName.getText().toUpperCase() + "\n");
            writer.write("Last Name: " + tfLastName.getText().toUpperCase() + "\n");
            writer.write("Phone Number: " + tfPhoneNum.getText() + "\n");
            writer.write("Address: " + tfAddress.getText() + "\n");
            writer.write("NID Number: " + tfNidNum.getText() + "\n");

            // Checkboxes
            writer.write("Safety Training: " + (safetyTrainingCheckBox.isSelected() ? "Yes" : "No") + "\n");
            writer.write("Operational Training: " + (operationalTrainingCheckBox.isSelected() ? "Yes" : "No") + "\n");
            writer.write("Technical Training: " + (technicalTrainingCheckBox.isSelected() ? "Yes" : "No") + "\n");
            writer.write("Healthcare: " + (healthcareCheckBox.isSelected() ? "Yes" : "No") + "\n");
            writer.write("Financial Wellness: " + (financialWellnessCheckBox.isSelected() ? "Yes" : "No") + "\n");
            writer.write("Vocational Training: " + (vocationalTrainingCheckBox.isSelected() ? "Yes" : "No") + "\n");
            writer.write("Skill Improvement Training: " + (skillImprovementTrainingCheckBox.isSelected() ? "Yes" : "No") + "\n");

            // Combobox
            writer.write("Employee Type: " + cboxEmployeetype.getSelectedItem() + "\n");

            // Radio buttons
            writer.write("Gender: ");
            if (maleRadioButton.isSelected()) {
                writer.write("Male");
            } else if (femaleRadioButton.isSelected()) {
                writer.write("Female");
            } else if (otherRadioButton.isSelected()) {
                writer.write("Other");
            }
            writer.write("\n");

            // Radio buttons
            writer.write("Status: ");
            if (approvedRadioButton.isSelected()) {
                writer.write("Approved");
            } else if (pendingRadioButton.isSelected()) {
                writer.write("Pending");
            }
            writer.write("\n");
            // Textarea
            writer.write("Reasoning: " + tAreaReasoning.getText() + "\n");

            // Add more data as needed...

            writer.write("-".repeat(50)+"\n"); // Separate entries with a newline
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getStatus() {
        if (approvedRadioButton.isSelected()) {
            return "Approved";
        } else if (pendingRadioButton.isSelected()) {
            return "Pending";
        }
        return null;
    }

    public static void main(String[] args) {
  }
}
