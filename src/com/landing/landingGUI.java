package com.landing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class landingGUI {
    private JButton btnAdmin;
    private JLabel iconLabel;
    private JButton btnStatus;
    private JButton btnApply;
    private JPanel mainPanel;

    public landingGUI() {
        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("landingGUI");
        frame.setContentPane(new landingGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
        frame.setBounds(100, 100, 450, 300);
        frame.pack();
        frame.setVisible(true);
    }
}
