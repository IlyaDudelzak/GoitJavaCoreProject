package com.dudelzak.goit.java.coreproject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StartingWindow {
    JFrame frame;
    JLabel welcomeLabel;
    JLabel subtitleLabel;
    JButton startButton;

    public StartingWindow() {
        frame = new JFrame("Cities");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 115);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(Utils.getIcon());

        // Horizontal layout with BorderLayout
        JPanel rootPanel = new JPanel(new BorderLayout(16, 0));
        rootPanel.setBackground(UITheme.COLOR_BACKGROUND);
        rootPanel.setBorder(new EmptyBorder(12, 18, 12, 18));

        // Left side: Title and brief subtitle
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 2));
        textPanel.setOpaque(false);

        welcomeLabel = new JLabel("Welcome to Cities!");
        welcomeLabel.setFont(UITheme.FONT_TITLE);
        welcomeLabel.setForeground(UITheme.COLOR_TEXT_PRIMARY);

        subtitleLabel = new JLabel("Chain world cities by last letter");
        subtitleLabel.setFont(UITheme.FONT_SUBTITLE);
        subtitleLabel.setForeground(UITheme.COLOR_TEXT_SECONDARY);

        textPanel.add(welcomeLabel);
        textPanel.add(subtitleLabel);
        rootPanel.add(textPanel, BorderLayout.CENTER);

        // Right side: Start button vertically centered
        JPanel buttonWrapper = new JPanel(new GridBagLayout());
        buttonWrapper.setOpaque(false);

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 36));
        UITheme.stylePrimaryButton(startButton);
        startButton.addActionListener(_ -> {
            setVisibility(false);
            AppLauncher.getInstance().showGameWindow();
        });
        buttonWrapper.add(startButton);
        rootPanel.add(buttonWrapper, BorderLayout.EAST);

        frame.setContentPane(rootPanel);
        frame.setVisible(true);
    }

    public void setVisibility(boolean visibility) {
        frame.setVisible(visibility);
    }
}