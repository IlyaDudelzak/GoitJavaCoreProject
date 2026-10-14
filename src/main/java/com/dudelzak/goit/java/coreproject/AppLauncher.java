package com.dudelzak.goit.java.coreproject;

import javax.swing.*;

public class AppLauncher {
    static AppLauncher instance;
    private StartingWindow startingWindow;
    private GameWindow gameWindow;
    private CityNameStorage storage;
    private GameManager gameManager;

    public static AppLauncher getInstance() {
        if(instance == null) instance = new AppLauncher();
        return instance;
    }

    static void main(String[] args) {
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
        storage = new CityNameStorage();
        gameManager = new GameManager(storage);

        gameWindow = new GameWindow(gameManager);
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public CityNameStorage getStorage() {
        return storage;
    }
}