package com.dudelzak.goit.java.coreproject;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameWindow {
    GameManager manager;
    private int moveCount = 0;

    JFrame frame;
    JTextField inputField;
    JLabel statusLabel, currentLetterLabel;
    JTextArea historyArea;
    JButton confirmButton, surrenderButton;

    public GameWindow(GameManager manager) {
        this.manager = manager;

        frame = new JFrame("Cities Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(440, 540);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(Utils.getIcon());
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(UITheme.COLOR_BACKGROUND);

        // Top info card
        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setBorder(new EmptyBorder(12, 14, 4, 14));
        topWrapper.setOpaque(false);

        JPanel topCard = new JPanel(new GridLayout(2, 1, 4, 4));
        topCard.setBackground(UITheme.COLOR_SURFACE);
        topCard.setBorder(new CompoundBorder(
                new LineBorder(UITheme.COLOR_BORDER, 1),
                new EmptyBorder(10, 12, 10, 12)
        ));

        currentLetterLabel = new JLabel("Enter any world city to start the game", SwingConstants.CENTER);
        currentLetterLabel.setFont(UITheme.FONT_BOLD);
        currentLetterLabel.setForeground(UITheme.COLOR_PRIMARY);

        statusLabel = new JLabel("Turns: 0  |  Score: You 0 — Comp 0", SwingConstants.CENTER);
        statusLabel.setFont(UITheme.FONT_SUBTITLE);
        statusLabel.setForeground(UITheme.COLOR_TEXT_SECONDARY);

        topCard.add(currentLetterLabel);
        topCard.add(statusLabel);
        topWrapper.add(topCard, BorderLayout.CENTER);
        frame.add(topWrapper, BorderLayout.NORTH);

        // Center history area
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setLineWrap(true);
        historyArea.setWrapStyleWord(true);
        UITheme.styleTextArea(historyArea);

        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(4, 14, 4, 14),
                new LineBorder(UITheme.COLOR_BORDER, 1)
        ));
        scrollPane.setBackground(UITheme.COLOR_BACKGROUND);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Bottom controls panel
        JPanel bottomPanel = new JPanel(new BorderLayout(0, 8));
        bottomPanel.setBorder(new EmptyBorder(4, 14, 14, 14));
        bottomPanel.setOpaque(false);

        // Input row
        JPanel inputPanel = new JPanel(new BorderLayout(8, 0));
        inputPanel.setOpaque(false);
        inputField = new JTextField();
        UITheme.styleTextField(inputField);

        confirmButton = new JButton("Submit");
        confirmButton.setPreferredSize(new Dimension(96, 36));
        UITheme.stylePrimaryButton(confirmButton);

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(confirmButton, BorderLayout.EAST);

        // Action buttons row (Auto-move & Surrender)
        JPanel actionButtonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        actionButtonsPanel.setOpaque(false);

        surrenderButton = new JButton("Surrender");
        surrenderButton.setPreferredSize(new Dimension(140, 34));
        UITheme.styleDangerButton(surrenderButton);

        actionButtonsPanel.add(surrenderButton);

        bottomPanel.add(inputPanel, BorderLayout.NORTH);
        bottomPanel.add(actionButtonsPanel, BorderLayout.SOUTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Event listeners
        inputField.addActionListener(e -> confirmButton.doClick());

        confirmButton.addActionListener(e -> {
            String playerText = inputField.getText().trim();
            if (!playerText.isEmpty()) {
                MoveResult result = manager.processMove(playerText);

                if (result.shouldContinue()) {
                    moveCount++;
                    historyArea.append("You: " + playerText + "\n");
                    historyArea.append("Computer: " + manager.getLastComputerMove() + "\n\n");

                    historyArea.setCaretPosition(historyArea.getDocument().getLength());

                    String nextChar = Utils.getValidLastLetter(manager.getLastComputerMove());
                    currentLetterLabel.setText("Your next city must start with: " + (nextChar.isEmpty() ? "?" : nextChar));
                    statusLabel.setText("Turns: " + moveCount + "  |  Score: You " + manager.getPlayerScore() + " — Comp " + manager.getCompScore());
                    inputField.setText("");
                }

                if (result.gameEnd()) {
                    AppLauncher.getInstance().showStartWindow();
                    dispose();
                }
            }
        });


        surrenderButton.addActionListener(e -> {
            MoveResult result = manager.processMove("give up");
            if (result.gameEnd()) {
                AppLauncher.getInstance().showStartWindow();
                dispose();
            }
        });

        frame.setVisible(true);
    }

    public void dispose() {
        frame.dispose();
    }
}