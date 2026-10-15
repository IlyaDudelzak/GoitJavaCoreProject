package com.dudelzak.goit.java.coreproject;

import javax.swing.*;

public class AppLauncher {
    static AppLauncher instance;
    private StartingWindow startingWindow;

    public static AppLauncher getInstance() {
        if(instance == null) instance = new AppLauncher();
        return instance;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UITheme.setupTheme();
            getInstance().showStartWindow();
        });
    }

    public void showStartWindow() {
        if(startingWindow == null)
            startingWindow = new StartingWindow();
        startingWindow.setVisibility(true);
    }

    public void showGameWindow() {
        CityNameStorage storage = new CityNameStorage();
        GameManager gameManager = new GameManager(storage);
        new GameWindow(gameManager);
    }
}