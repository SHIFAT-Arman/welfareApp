package com.landing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JTextField tfUsername;
    private JPasswordField pfUserPassword;
    private JButton btnSubmit;
    private JButton btnGoBack;
    private JPanel loginPanel;

    JFrame frame = new JFrame("Login");
    public Login(){
        frame.setContentPane(loginPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 450, 300);
        frame.pack();
        frame.setVisible(true);

        btnGoBack.addActionListener(e -> {
            landingGUI landing = new landingGUI();
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
                    registration reg = new registration();
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
