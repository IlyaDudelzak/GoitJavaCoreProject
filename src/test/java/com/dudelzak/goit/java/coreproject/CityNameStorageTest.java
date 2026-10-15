package com.dudelzak.goit.java.coreproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityNameStorageTest {

    @Test
    @DisplayName("getNameByPlayerMove should pick a city starting with the valid last letter")
    void testGetNameByPlayerMove() {
        CityNameStorage storage = new CityNameStorage();
        // Tokyo ends with O
        String nextCity = storage.getNameByPlayerMove("Tokyo", false);
        assertNotNull(nextCity);
        assertTrue(nextCity.toUpperCase().startsWith("O"), "Should start with 'O', got: " + nextCity);
    }

    @Test
    @DisplayName("getNameByPlayerMove should skip trailing punctuation/spaces and find matching city")
    void testGetNameByPlayerMoveWithTrailingSymbols() {
        CityNameStorage storage = new CityNameStorage();
        // "London  " ends with N
        String nextCity = storage.getNameByPlayerMove("London  ", false);
        assertNotNull(nextCity);
        assertTrue(nextCity.toUpperCase().startsWith("N"), "Should start with 'N', got: " + nextCity);
    }

    @Test
    @DisplayName("getNameByFirstLetter should find a city starting with specified prefix")
    void testGetNameByFirstLetter() {
        CityNameStorage storage = new CityNameStorage();
        String city = storage.getNameByFirstLetter("P", false);
        assertNotNull(city);
        assertTrue(city.toUpperCase().startsWith("P"), "Should start with 'P', got: " + city);
    }

    @Test
    @DisplayName("removeCityName and containsName should work with normalized variations")
    void testNormalizedLookupAndRemoval() {
        CityNameStorage storage = new CityNameStorage();
        assertTrue(storage.containsName("New   York"));
        assertTrue(storage.containsName("new york"));
        assertTrue(storage.removeCityName("  NEW   YORK  "));
        assertFalse(storage.containsName("New York"));
    }
}
