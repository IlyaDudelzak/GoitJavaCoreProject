package com.dudelzak.goit.java.coreproject;

import javax.swing.*;

public class StartingWindow {
    JFrame frame;
    JLabel welcomeLabel;
    JComboBox<String> languageCombo;
    JButton startButton;

    public StartingWindow() {
        frame = new JFrame("Cities");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(440, 120);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(Utils.getIcon());

        welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setBounds(20, 20, 150, 40);
        frame.add(welcomeLabel);

        startButton = new JButton("Start");
        startButton.setBounds(300, 20, 100, 40);
        startButton.addActionListener(e -> {
            frame.dispose();
            new GameWindow();
        });
        frame.add(startButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartingWindow::new);
    }
}