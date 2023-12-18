package com.landing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingGUI extends JFrame{
    private JButton btnAdmin,btnStatus ,btnApply;
    private JLabel iconLabel;
    private JPanel mainPanel;

    JFrame frame;
    public LandingGUI() {
        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                frame.dispose();

            }
        });
        btnStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeDataForm employeeDataForm = new EmployeeDataForm();
                employeeDataForm.setVisible(true);

            }
        });
        btnApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplyForm applyForm = new ApplyForm();
            }
        });

        frame = new JFrame("Welfare Management System");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 450, 300);
        //frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//
//    }
}
