package com.landing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class registration extends JFrame{
    private JTextField tfFirstName, tfLastName, tfPhoneNum, tfAddress, tfNidNum;
    private JCheckBox safetyTrainingCheckBox, operationalTrainingCheckBox, technicalTrainingCheckBox;
    private JComboBox<String> cboxEmployeetype;
    private JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
    private JTextArea tAreaReasoning;
    private JCheckBox healthcareCheckBox, financialWellnessCheckBox, vocationalTrainingCheckBox, skillImprovementTrainingCheckBox;
    private JButton btnSubmit, btnCancel;
    private JPanel regPanel;

    JFrame frame = new JFrame("Registration");
    public registration() {
        // Initialize components and set up the UI...
        frame.setContentPane(regPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);


        // Add action listener to the submit button
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    // Write data to file
                    writeDataToFile();

                    // For demonstration purposes, let's close the registration window
                    closeRegistrationWindow();
                    landingGUI landing = new landingGUI();
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
                // For demonstration purposes, let's close the registration window
                closeRegistrationWindow();
                landingGUI landing = new landingGUI();
            }
        });
    }

    private void closeRegistrationWindow() {
        // This method is called when the registration window should be closed
        frame = (JFrame) SwingUtilities.getWindowAncestor(btnCancel);
        frame.dispose();
    }

    private boolean validateFields() {
        // Validate text fields
        if (isEmpty(tfFirstName) || !isAlpha(tfFirstName.getText()) ||
                isEmpty(tfLastName) || !isAlpha(tfLastName.getText()) ||
                isEmpty(tfPhoneNum) || !isNumeric(tfPhoneNum.getText()) ||
                isEmpty(tfAddress) || isEmpty(tfNidNum) || !isNumeric(tfNidNum.getText())) {
            return false;
        }

        // Validate combo box
        if (cboxEmployeetype.getSelectedIndex() == -1) {
            return false;
        }

        // Validate radio buttons
        if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected() && !otherRadioButton.isSelected()) {
            return false;
        }

        // Validate text area
        if (tAreaReasoning.getText().trim().isEmpty()) {
            return false;
        }

        // Validate checkboxes
        if (!healthcareCheckBox.isSelected() && !financialWellnessCheckBox.isSelected() && !vocationalTrainingCheckBox.isSelected() && !skillImprovementTrainingCheckBox.isSelected()) {
            return false;
        }

        // Validate checkboxes
        return safetyTrainingCheckBox.isSelected() || operationalTrainingCheckBox.isSelected() || technicalTrainingCheckBox.isSelected();


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

    private void writeDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employee_data.txt", true))) {
            // Append the data to the text file
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

            // Textarea
            writer.write("Reasoning:\n" + tAreaReasoning.getText() + "\n");

            // Add more data as needed...

            writer.write("-".repeat(50)+"\n"); // Separate entries with a newline
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    public static void main(String[] args) {
//        JFrame frame = new JFrame("Registration");
//        frame.setContentPane(new registration().regPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);


    }
}
