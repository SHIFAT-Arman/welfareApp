package com.landing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class Login extends JFrame{
    private JTextField tfUsername;
    private JPasswordField pfUserPassword;
    private JButton btnSubmit, btnGoBack;
    private JPanel loginPanel;


    JFrame frame = new JFrame("Login");
    public Login(){
        frame.setContentPane(loginPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        btnGoBack.addActionListener(e -> {
            LandingGUI landing = new LandingGUI();
            frame.dispose();
        });

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                char[] password = pfUserPassword.getPassword();
                // For demo purposes, check against predefined credentials
                if (checkCredentials(username, password)) {
                    JOptionPane.showMessageDialog(Login.this, "Login successful!");
                    Registration reg = new Registration();
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public boolean checkCredentials(String username, char[] password) {
        // For demo purposes, check against predefined credentials
        String expectedUsername = "admin";
        String expectedPassword = "admin";
        return expectedUsername.equals(username) && expectedPassword.equals(new String(password));
    }

    public static void main(String[] args) {
    }


}
