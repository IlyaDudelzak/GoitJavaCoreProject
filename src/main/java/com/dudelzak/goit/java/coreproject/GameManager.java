package com.dudelzak.goit.java.coreproject;

import javax.swing.*;
import java.util.Locale;

public class GameManager {
    CityNameStorage storage;
    String lastComputerMove = "";
    int compScore = 0, playerScore = 0;


    public GameManager() {
        this(new CityNameStorage());
    }

    public GameManager(CityNameStorage storage) {
        this.storage = storage;
    }

    public MoveResult makeMove(String playerMove) {
        if(playerMove.toLowerCase().contains("give up") || playerMove.toLowerCase().contains("здаюсь")) return MoveResult.PLAYER_GAVE_UP;
        String expectedLetter = Utils.getValidLastLetter(lastComputerMove);
        if(lastComputerMove.isEmpty() || playerMove.toUpperCase().startsWith(expectedLetter)) {
            if (storage.removeCityName(playerMove)) {
                if (storage.isEmpty()) return MoveResult.OUT_OF_NAMES;
                String computerMove = storage.getNameByPlayerMove(playerMove, false);
                if (computerMove != null) {
                    String possibleNextPlayerMove = storage.getNameByPlayerMove(computerMove, false);
                    if(possibleNextPlayerMove != null) {
                        storage.removeCityName(computerMove);
                        lastComputerMove = computerMove;
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

        if (result.gameEnd()) {
            playerScore += result.playerWon() ? 100 : 0;
            compScore += result.playerWon() ? 0 : 100;
            JOptionPane.showMessageDialog(
                    null,
                    result.getMessage() +
                            "\nYour score: " + playerScore +
                            "\nComputer score: " + compScore,
                    "Game Over",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else if (result.showMessage()) {
            JOptionPane.showMessageDialog(null, result.getMessage());
        }

        if (result.shouldContinue()) {
            playerScore += playerMove.length() * playerMove.length();
            compScore += lastComputerMove.length() * lastComputerMove.length();
        }
        return result;
    }
}
