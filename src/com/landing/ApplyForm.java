package com.landing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplyForm extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel tbPanel;
    private JTextArea a1ComprehensiveCoverageIncludingTextArea, a1FinancialEducationProgramsTextArea;
    private JButton btnHealthCare, btnApplyFinancial, btnApplyVocational, btnApplySkillImprovement;

    public ApplyForm() {
        JFrame frame = new JFrame("ApplyForm");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 450, 300);
        frame.pack();
        frame.setVisible(true);

        btnHealthCare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplyRegForm applyRegForm = new ApplyRegForm(ApplyForm.this);
                applyRegForm.setApplicationType("Health Care"); // Set the application type
            }
        });
        btnApplyFinancial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplyRegForm applyRegForm = new ApplyRegForm(ApplyForm.this);
                applyRegForm.setApplicationType("Financial"); // Set the application type
            }
        });
        btnApplyVocational.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplyRegForm applyRegForm = new ApplyRegForm(ApplyForm.this);
                applyRegForm.setApplicationType("Vocational"); // Set the application type
            }
        });
        btnApplySkillImprovement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplyRegForm applyRegForm = new ApplyRegForm(ApplyForm.this);
                applyRegForm.setApplicationType("Skill Improvement"); // Set the application type
            }
        });
    }

    public JButton getBtnHealthCare() {
        return btnHealthCare;
    }



    public static void main(String[] args) {

    }
}
