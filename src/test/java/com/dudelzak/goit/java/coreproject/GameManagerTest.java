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
    }

    @Test
    @DisplayName("processMove on surrender should award computer 100 bonus points")
    void testSurrenderScoring() {
        GameManager manager = new GameManager();
        assertEquals(0, manager.playerScore);
        assertEquals(0, manager.compScore);

        MoveResult result = manager.processMove("give up");
        assertEquals(MoveResult.PLAYER_GAVE_UP, result);
        assertEquals(0, manager.playerScore);
        assertEquals(100, manager.compScore);
    }
}
