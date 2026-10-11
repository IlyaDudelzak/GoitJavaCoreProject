package com.dudelzak.goit.java.coreproject;

import javax.swing.*;

public class GameManager {
    CityNameStorage storage;
    GameWindow gameWindow;
    int score = 0;


    public GameManager(GameWindow gameWindow) {
        this(gameWindow, new CityNameStorage());
    }

    public GameManager(GameWindow gameWindow, CityNameStorage storage) {
        this.gameWindow = gameWindow;
        this.storage = storage;
    }

    public MoveResult makeMove(String playerMove) {
        if (storage.removeCityName(playerMove)) {
            if (storage.isEmpty()) return MoveResult.OUT_OF_NAMES;
            String computerMove = storage.getNameByPlayerMove(playerMove, false);
            if (computerMove != null) {
                return MoveResult.NORMAL;
            } else {
                return MoveResult.NO_NAME_FOR_FINAL_LETTER;
            }
        } else {
            return MoveResult.NO_SUCH_NAME;
        }
    }

    public void processMove(String playerMove) {
        playerMove = playerMove.trim();
        if (playerMove.isEmpty()) return;

        MoveResult result = makeMove(playerMove);

        if (result.gameEnd()) {
            JOptionPane.showMessageDialog(gameWindow.frame, result.getMessage() + "\nScore: " + score);
        } else if (result.showMessage()) {
            JOptionPane.showMessageDialog(gameWindow.frame, result.getMessage());
        }

        if (result.shouldContinue()) {
            String compCity = storage.getNameByPlayerMove(playerMove, true);
            gameWindow.computerMoveLabel.setText("Computer: " + compCity);
            score += playerMove.length() * playerMove.length();
        }
        gameWindow.inputField.setText("");
    }
}
