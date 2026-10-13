package com.dudelzak.goit.java.coreproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsTest {

    @ParameterizedTest(name = "City \"{0}\" should yield valid last letter \"{1}\"")
    @CsvSource({
            "London, N",
            "Tokyo, O",
            "Paris, S",
            "Kyiv, V",
            "New York, K"
    })
    @DisplayName("Should extract correct last letter for regular English city names")
    void testStandardCityNames(String input, String expected) {
        assertEquals(expected, Utils.getValidLastLetter(input));
    }

    @ParameterizedTest(name = "Input \"{0}\" with symbols/spaces should yield \"{1}\"")
    @CsvSource({
            "'Xi''an', N",
            "'Xi''an''', N",
            "'Saint-Louis!', S",
            "'Rostov-on-Don   ', N",
            "'City-123', Y"
    })
    @DisplayName("Should skip non-letter characters like apostrophes, hyphens, and whitespace")
    void testAtypicalEndingsAndPunctuation(String input, String expected) {
        assertEquals(expected, Utils.getValidLastLetter(input));
    }

    @ParameterizedTest(name = "Cyrillic city \"{0}\" should skip disallowed letters and yield \"{1}\"")
    @CsvSource({
            "Тернопіль, Л",
            "Кривий Ріг, Г",
            "Вижній, І",
            "Тайбей, Е",
            "Пермь, М",
            "Рязань, Н",
            "Суздаль, Л"
    })
    @DisplayName("Should skip Cyrillic soft sign, short I, and other non-starting letters")
    void testSlavicSkippedLetters(String input, String expected) {
        assertEquals(expected, Utils.getValidLastLetter(input));
    }

    @Test
    @DisplayName("Should return empty string for null, blank, or input with no eligible letters")
    void testEdgeCases() {
        assertEquals("", Utils.getValidLastLetter(null));
        assertEquals("", Utils.getValidLastLetter(""));
        assertEquals("", Utils.getValidLastLetter("   "));
        assertEquals("", Utils.getValidLastLetter("---'''123"));
        assertEquals("", Utils.getValidLastLetter("ььь"));
    }
}
