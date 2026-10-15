package com.dudelzak.goit.java.coreproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CityReaderTest {

    @Test
    @DisplayName("getCities should load a non-empty list of cities from cities.json")
    void testGetCitiesLoadsValidList() {
        List<String> cities = CityReader.getCities();
        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        assertTrue(cities.contains("Kyiv"));
        assertTrue(cities.contains("London"));
    }
}
