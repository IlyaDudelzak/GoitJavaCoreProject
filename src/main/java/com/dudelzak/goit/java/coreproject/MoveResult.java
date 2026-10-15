package com.dudelzak.goit.java.coreproject;

public enum MoveResult {
    NO_MOVE(false, false, false, null),
    NORMAL(true, false, false, null),
    NO_SUCH_NAME(false, false, false, "No such city name, or repeat"),
    FIRST_CHAR_PMOVE_NOT_FINAL_COMPMOVE_CHAR(false, false, false, "The first character of your guess does not match the final character of computer`s last move."),
    OUT_OF_NAMES(false, true, true, "Out of names! You Won!"),
    NO_NAME_FOR_FINAL_LETTER(false, true, true, "There is no name for the final letter of your guess. You Won!"),
    NO_NAME_FOR_FINAL_COMP_LETTER(false, true, false, "There is no name for the final letter of computer`s guess, You lost."),
    PLAYER_GAVE_UP(false, true, false, "You gave up. Computer won.");

    private final boolean shouldContinue;
    private final boolean gameEnd;
    private final boolean playerWon;
    private final String message;


    MoveResult(boolean shouldContinue, boolean gameEnd, boolean playerWon, String message) {
        this.shouldContinue = shouldContinue;
        this.gameEnd = gameEnd;
        this.playerWon = playerWon;
        this.message = message;
    }

    public boolean shouldContinue() { return shouldContinue; }
    public boolean gameEnd() { return gameEnd; }
    public boolean playerWon() { return playerWon; }
    public String getMessage() { return message; }
    public boolean showMessage() { return !gameEnd && message != null; }
}