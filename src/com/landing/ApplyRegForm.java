package com.landing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ApplyRegForm extends JFrame{
    private String applicationType;
    private ApplyForm applyForm;
    private JPanel applyFormPanel;
    private JTextField tfName;
    private JTextField tfNumber;
    private JButton btnApply;
    private JButton btnCancel;

    JFrame frame;
    public ApplyRegForm(ApplyForm applyForm) {
        this.applyForm = applyForm;
        JFrame frame = new JFrame("ApplyRegForm");
        frame.setContentPane(applyFormPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 450, 300);
//        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        btnApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    writeData();
                }

            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeRegistrationWindow();

            }
        });
    }
    private boolean validateFields(){
        if (isEmpty(tfNumber) || !isNumeric(tfNumber.getText()) || !isValidPhoneNumber(tfNumber.getText())) {
            showErrorDialog("Phone Number should be a valid 11-digit number starting with zero.");
            return false;
        }
        if(isEmpty(tfName) || !isAlpha(tfName.getText())){
            showErrorDialog("Name should only contain alphabetic characters.");
            return false;
        }
        return true;
    }
    private void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
    private boolean isEmpty(JTextField textField) {
        return textField.getText().trim().isEmpty();
    }
    private boolean isAlpha(String input) {
        return input.matches("^[a-zA-Z]+$");
    }
    private boolean isNumeric(String input) {
        return input.matches("^[0-9]+$");
    }

    private boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]{5,}$");
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^01[0-9]{9}$");
    }

    private void closeRegistrationWindow() {
        // This method is called when the Registration window should be closed
        frame = (JFrame) SwingUtilities.getWindowAncestor(btnCancel);
        frame.dispose();
    }

    private void writeData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employee_apply_data.txt", true))) {

                writer.newLine();
                writer.write("--------------------------------------------------");
                writer.newLine();
                writer.write("Name: " + tfName.getText());
                writer.newLine();
                writer.write("Phone Number: " + tfNumber.getText());
                writer.newLine();
                writer.write("Status: Pending");
                writer.newLine();
                writer.write("Applied for: " + applicationType);
                writer.newLine();
                writer.write("--------------------------------------------------");
                writer.newLine();
                JOptionPane.showMessageDialog(null, "Application successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                closeRegistrationWindow();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while writing data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }
    public static void main(String[] args) {

    }
}
