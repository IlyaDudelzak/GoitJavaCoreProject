package com.dudelzak.goit.java.coreproject;

import javax.swing.*;

public class GameWindow {
    GameManager manager;

    JFrame frame;
    JTextField inputField;
    JLabel inputLabel, computerMoveLabel;
    JButton confirmButton;

    public GameWindow() {
        this.manager = new GameManager(this);

        frame = new JFrame("Game - Cities");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320, 160);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(Utils.getIcon());

        inputLabel = new JLabel("City:");
        inputLabel.setBounds(10, 10, 100, 25);
        frame.add(inputLabel);

        inputField = new JTextField();
        inputField.setBounds(110, 10, 180, 25);
        inputField.addActionListener(e -> confirmButton.doClick());
        frame.add(inputField);

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(110, 40, 180, 25);
        confirmButton.addActionListener(e -> manager.processMove(inputField.getText()));
        frame.add(confirmButton);

        computerMoveLabel = new JLabel("Computer move:");
        computerMoveLabel.setBounds(10, 75, 290, 25);
        frame.add(computerMoveLabel);

        frame.setVisible(true);

    }
}
