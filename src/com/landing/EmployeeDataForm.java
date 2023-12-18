package com.landing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeDataForm extends JFrame {
    private JPanel panel1;
    private JTable employeeTable;
    private JScrollPane js;
    private JTable applyTable;
    private JScrollPane js2;

    Registration registration;

    public EmployeeDataForm() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("First Name");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Status");

        employeeTable.setModel(tableModel);

        JTableHeader header = employeeTable.getTableHeader();
        Font headerFont = new Font("Century Gothic", Font.BOLD, 18); // You can adjust the font and size accordingly
        header.setFont(headerFont);
        js.setViewportView(employeeTable);

        readDataFromFileRegistration(tableModel);

        DefaultTableModel tableModel2 = new DefaultTableModel();
        tableModel2.addColumn("Name");
        tableModel2.addColumn("Phone Number");
        tableModel2.addColumn("Applied for");

        applyTable.setModel(tableModel2);

        JTableHeader header2 = applyTable.getTableHeader();
        Font headerFont2 = new Font("Century Gothic", Font.BOLD, 18); // You can adjust the font and size accordingly
        header2.setFont(headerFont2);
        js2.setViewportView(applyTable);

        readDataFromFileApply(tableModel2);

        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void readDataFromFileApply(DefaultTableModel tableModel2){
        try (BufferedReader reader = new BufferedReader(new FileReader("employee_apply_data.txt"))){
            String line;
            String name = null;
            String phoneNumber = null;
            String appliedFor = null;

            while ((line = reader.readLine()) != null){
                if (line.equals("--------------------------------------------------")){
                    if (name != null && phoneNumber != null && appliedFor != null){
                        String[] rowData = {name, phoneNumber, appliedFor};
                        tableModel2.addRow(rowData);
                    }
                    name = null;
                    phoneNumber = null;
                    appliedFor = null;
                }else {
                    String[] parts = line.split(": ");
                    if (parts.length == 2){
                        String key = parts[0].trim();
                        String value = parts[1].trim();

                        switch (key){
                            case "Name":
                                name = value;
                                break;
                            case "Phone Number":
                                phoneNumber = value;
                                break;
                            case "Applied for":
                                appliedFor = value;
                                break;
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readDataFromFileRegistration(DefaultTableModel tableModel) {
        try (BufferedReader reader = new BufferedReader(new FileReader("employee_data.txt"))) {
            String line;
            String firstName = null;
            String phoneNumber = null;
            String status = null;

            while ((line = reader.readLine()) != null) {
                if (line.equals("--------------------------------------------------")) {
                    if (firstName != null && phoneNumber != null && status != null) {
                        String[] rowData = {firstName, phoneNumber, status}; // Initialize with an empty status
                        tableModel.addRow(rowData);
                    }
                    firstName = null;
                    phoneNumber = null;
                    status = null;
                } else {
                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();

                        switch (key) {
                            case "First Name":
                                firstName = value;
                                break;
                            case "Phone Number":
                                phoneNumber = value;
                                break;
                            case "Status":
                                status = value;
                                break;

                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        new EmployeeDataForm();

    }



}
