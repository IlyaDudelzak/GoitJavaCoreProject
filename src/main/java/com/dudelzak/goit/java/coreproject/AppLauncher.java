package com.dudelzak.goit.java.coreproject;

import javax.swing.*;

public class AppLauncher {
    static void main(String[] args) {
        UITheme.setupTheme();
        SwingUtilities.invokeLater(StartingWindow::new);
    }
}
