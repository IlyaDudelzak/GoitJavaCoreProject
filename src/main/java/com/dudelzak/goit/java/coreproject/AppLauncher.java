package com.dudelzak.goit.java.coreproject;

import javax.swing.*;

public class AppLauncher {
    static void main(String[] args) {
        SwingUtilities.invokeLater(StartingWindow::new);
    }
}
