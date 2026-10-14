package com.dudelzak.goit.java.coreproject;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Centralized UI Theme and Styling configuration.
 * Configures application-wide Look and Feel, colors, typography, and component styling.
 */
public class UITheme {

    // Modern color palette
    public static final Color COLOR_PRIMARY = new Color(37, 99, 235);       // #2563EB Vibrant Blue
    public static final Color COLOR_SECONDARY = new Color(100, 116, 139);   // #64748B Slate Grey
    public static final Color COLOR_DANGER = new Color(220, 38, 38);        // #DC2626 Red
    public static final Color COLOR_BACKGROUND = new Color(248, 250, 252);  // #F8FAFC Soft off-white
    public static final Color COLOR_SURFACE = Color.WHITE;
    public static final Color COLOR_BORDER = new Color(203, 213, 225);      // #CBD5E1 Light border
    public static final Color COLOR_TEXT_PRIMARY = new Color(15, 23, 42);   // #0F172A Dark Slate
    public static final Color COLOR_TEXT_SECONDARY = new Color(71, 85, 105);// #475569 Muted Slate

    // Typography
    public static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 18);
    public static final Font FONT_SUBTITLE = new Font("SansSerif", Font.PLAIN, 12);
    public static final Font FONT_BOLD = new Font("SansSerif", Font.BOLD, 13);
    public static final Font FONT_REGULAR = new Font("SansSerif", Font.PLAIN, 13);
    public static final Font FONT_BUTTON = new Font("SansSerif", Font.BOLD, 12);
    public static final Font FONT_HISTORY = new Font("SansSerif", Font.PLAIN, 13);

    /**
     * Initializes global application Look and Feel and Swing UI defaults.
     */
    public static void setupTheme() {
        try {
            System.setProperty("awt.useSystemAAFontSettings", "on");
            System.setProperty("swing.aatext", "true");

            // Prefer Nimbus or System L&F for modern rendering
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Fallback to default Metal if system L&F fails
            }
        }

        // Apply custom global defaults
        UIManager.put("Panel.background", COLOR_BACKGROUND);
        UIManager.put("Label.foreground", COLOR_TEXT_PRIMARY);
        UIManager.put("Label.font", FONT_REGULAR);
    }

    public static void stylePrimaryButton(JButton button) {
        button.setFont(FONT_BUTTON);
        button.setBackground(COLOR_PRIMARY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new CompoundBorder(
                new LineBorder(COLOR_PRIMARY, 1, true),
                new EmptyBorder(8, 14, 8, 14)
        ));
    }

    public static void styleSecondaryButton(JButton button) {
        button.setFont(FONT_BUTTON);
        button.setBackground(COLOR_SECONDARY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new CompoundBorder(
                new LineBorder(COLOR_SECONDARY, 1, true),
                new EmptyBorder(8, 12, 8, 12)
        ));
    }

    public static void styleDangerButton(JButton button) {
        button.setFont(FONT_BUTTON);
        button.setBackground(COLOR_DANGER);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new CompoundBorder(
                new LineBorder(COLOR_DANGER, 1, true),
                new EmptyBorder(8, 12, 8, 12)
        ));
    }

    public static void styleTextField(JTextField textField) {
        textField.setFont(FONT_REGULAR);
        textField.setBackground(COLOR_SURFACE);
        textField.setForeground(COLOR_TEXT_PRIMARY);
        textField.setCaretColor(COLOR_PRIMARY);
        textField.setBorder(new CompoundBorder(
                new LineBorder(COLOR_BORDER, 1),
                new EmptyBorder(6, 10, 6, 10)
        ));
    }

    public static void styleTextArea(JTextArea textArea) {
        textArea.setFont(FONT_HISTORY);
        textArea.setBackground(COLOR_SURFACE);
        textArea.setForeground(COLOR_TEXT_PRIMARY);
        textArea.setMargin(new Insets(8, 10, 8, 10));
    }
}
