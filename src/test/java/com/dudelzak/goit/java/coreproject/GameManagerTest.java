package com.dudelzak.goit.java.coreproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    @Test
    @DisplayName("makeMove should recognize surrender keywords in English and Ukrainian")
    void testSurrenderKeywords() {
        GameManager manager = new GameManager();

        MoveResult resultEn = manager.makeMove("give up");
        assertEquals(MoveResult.PLAYER_GAVE_UP, resultEn);
        assertTrue(resultEn.gameEnd());
        assertFalse(resultEn.playerWon());

        MoveResult resultUa = manager.makeMove("здаюсь");
        assertEquals(MoveResult.PLAYER_GAVE_UP, resultUa);
        assertTrue(resultUa.gameEnd());
        assertFalse(resultUa.playerWon());

        assertNotEquals(MoveResult.PLAYER_GAVE_UP, manager.makeMove("не здаюсь"));
        assertNotEquals(MoveResult.PLAYER_GAVE_UP, manager.makeMove("never give up"));
    }

    @Test
    @DisplayName("processMove on surrender should award computer 100 bonus points")
    void testSurrenderScoring() {
        GameManager manager = new GameManager();
        assertEquals(0, manager.getPlayerScore());
        assertEquals(0, manager.getCompScore());

        MoveResult result = manager.processMove("give up");
        assertEquals(MoveResult.PLAYER_GAVE_UP, result);
        assertEquals(0, manager.getPlayerScore());
        assertEquals(100, manager.getCompScore());
    }

    @Test
    @DisplayName("Empty or null input should return NO_MOVE with no continuation, no win, and no message")
    void testNoMoveResult() {
        GameManager manager = new GameManager();

        MoveResult resultNull = manager.makeMove(null);
        assertEquals(MoveResult.NO_MOVE, resultNull);
        assertFalse(resultNull.shouldContinue());
        assertFalse(resultNull.gameEnd());
        assertFalse(resultNull.playerWon());
        assertFalse(resultNull.showMessage());

        MoveResult resultEmpty = manager.processMove("   ");
        assertEquals(MoveResult.NO_MOVE, resultEmpty);
        assertFalse(resultEmpty.shouldContinue());
        assertFalse(resultEmpty.gameEnd());
        assertFalse(resultEmpty.playerWon());
        assertFalse(resultEmpty.showMessage());
    }
}
