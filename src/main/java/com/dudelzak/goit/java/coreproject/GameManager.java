package com.dudelzak.goit.java.coreproject;

import javax.swing.*;

public class GameManager {
    private final CityNameStorage storage;
    private String lastComputerMove = "";
    private int compScore = 0, playerScore = 0;


    public GameManager() {
        this(new CityNameStorage());
    }

    public GameManager(CityNameStorage storage) {
        this.storage = storage;
    }

    public MoveResult makeMove(String playerMove) {
        if (playerMove == null) return MoveResult.NO_MOVE;
        String trimmed = playerMove.trim();
        if (trimmed.equalsIgnoreCase("give up")
                || trimmed.equalsIgnoreCase("здаюсь")
                || trimmed.equalsIgnoreCase("сдаюсь")) {
            return MoveResult.PLAYER_GAVE_UP;
        }
        String expectedLetter = Utils.getValidLastLetter(lastComputerMove);
        if(lastComputerMove.isEmpty() || trimmed.toUpperCase().startsWith(expectedLetter)) {
            if (storage.removeCityName(trimmed)) {
                if (storage.isEmpty()) return MoveResult.OUT_OF_NAMES;
                String computerMove = storage.getNameByPlayerMove(playerMove, false);
                if (computerMove != null) {
                    String possibleNextPlayerMove = storage.getNameByPlayerMove(computerMove, false);
                    storage.removeCityName(computerMove);
                    lastComputerMove = computerMove;
                    if(possibleNextPlayerMove != null) {
                        return MoveResult.NORMAL;
                    } else {
                        return MoveResult.NO_NAME_FOR_FINAL_COMP_LETTER;
                    }
                } else {
                    return MoveResult.NO_NAME_FOR_FINAL_LETTER;
                }
            } else {
                return MoveResult.NO_SUCH_NAME;
            }
        } else {
            return MoveResult.FIRST_CHAR_PMOVE_NOT_FINAL_COMPMOVE_CHAR;
        }
    }

    public MoveResult processMove(String playerMove) {
        playerMove = playerMove.trim();
        if (playerMove.isEmpty()) return MoveResult.NO_MOVE;

        MoveResult result = makeMove(playerMove);

        if (result.shouldContinue() || result.gameEnd()) {
            if(result != MoveResult.PLAYER_GAVE_UP)
                playerScore += playerMove.length() * playerMove.length();
            if(!result.playerWon() && result != MoveResult.PLAYER_GAVE_UP)
                compScore += lastComputerMove.length() * lastComputerMove.length();
        }

        if (result.gameEnd()) {
            playerScore += result.playerWon() ? 100 : 0;
            compScore += result.playerWon() ? 0 : 100;
            if (!java.awt.GraphicsEnvironment.isHeadless()) {
                JOptionPane.showMessageDialog(
                        null,
                        result.getMessage() +
                                "\nYour score: " + playerScore +
                                "\nComputer score: " + compScore,
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        } else if (result.showMessage()) {
            if (!java.awt.GraphicsEnvironment.isHeadless()) {
                JOptionPane.showMessageDialog(null, result.getMessage());
            }
        }

        return result;
    }

    public int getCompScore() {
        return compScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public String getLastComputerMove() {
        return lastComputerMove;
    }
}
