package com.dudelzak.goit.java.coreproject;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Set;

public class Utils {
    /**
     * Set of letters that traditionally cannot start a city name in the game of Cities
     * (e.g., Cyrillic soft/hard signs and letters like 'ь', 'ъ', 'ы', 'й').
     */
    private static final Set<Character> INVALID_STARTING_LETTERS = Set.of(
            'ь', 'Ь',
            'ъ', 'Ъ',
            'ы', 'Ы',
            'й', 'Й'
    );

    public static Image getIcon() {
        URL url = Utils.class.getResource("/icon.png");
        if (url == null) {
            throw new IllegalArgumentException("Файл /icon.png не найден");
        }
        return new ImageIcon(url).getImage();
    }

    /**
     * Determines the valid last letter of a city name according to the game rules.
     * Iterates from the end backwards, skipping non-letter symbols (such as apostrophes,
     * hyphens, spaces) and letters that traditionally cannot start a city name (like 'ь', 'ъ', 'ы', 'й').
     *
     * @param str the city name
     * @return the valid last letter as an uppercase String, or empty string if none found
     */
    public static String getValidLastLetter(String str) {
        if (str == null || str.isBlank()) {
            return "";
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if (Character.isLetter(ch) && !INVALID_STARTING_LETTERS.contains(ch)) {
                return String.valueOf(Character.toUpperCase(ch));
            }
        }
        return "";
    }

    /**
     * Normalizes a city name for robust comparison and lookup:
     * - Trims leading/trailing whitespace
     * - Unifies various apostrophes ('’'`´ʼʻ) to standard single quote '
     * - Unifies various hyphens and dashes (–—−‐‑) to standard hyphen -
     * - Removes redundant spaces around hyphens
     * - Collapses multiple whitespace characters into a single space
     * - Converts to lowercase using Locale.ROOT
     *
     * @param name raw city name
     * @return normalized canonical city name
     */
    public static String normalizeCityName(String name) {
        if (name == null || name.isBlank()) {
            return "";
        }
        return name.trim()
                .replaceAll("[’'`´ʼʻ]", "'")
                .replaceAll("[–—−‐‑]", "-")
                .replaceAll("\\s*-\\s*", "-")
                .replaceAll("\\s+", " ")
                .toLowerCase(java.util.Locale.ROOT);
    }
}