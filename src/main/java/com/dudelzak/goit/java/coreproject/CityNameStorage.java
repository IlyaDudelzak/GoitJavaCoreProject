package com.dudelzak.goit.java.coreproject;

import java.util.List;
import java.util.Random;

public class CityNameStorage {
    public static Random random = new Random();

    private final List<String> cityNamesList;

    public CityNameStorage() {
        this.cityNamesList = CityReader.getCities();
    }

    public boolean isEmpty() {
        return cityNamesList.isEmpty();
    }

    public String getNameByFirstLetter(String prefix, boolean remove) {
        if (prefix == null || prefix.isEmpty()) return null;
        String searchPrefix = prefix.substring(0, 1).toUpperCase();
        List<String> possibleNames = cityNamesList.stream()
                .filter(s -> s.toUpperCase().startsWith(searchPrefix))
                .toList();
        if (!possibleNames.isEmpty()) {
            String name = possibleNames.get(random.nextInt(possibleNames.size()));
            if (remove) removeCityName(name);
            return name;
        }
        return null;
    }

    public String getNameByPlayerMove(String playerMove, boolean remove) {
        if (playerMove == null) return null;
        playerMove = playerMove.trim();
        if (playerMove.isEmpty()) return null;

        String targetLetter = Utils.getValidLastLetter(playerMove);
        if (targetLetter.isEmpty()) return null;

        return getNameByFirstLetter(targetLetter, remove);
    }

    public boolean removeCityName(String name) {
        return cityNamesList.removeIf(cityName -> cityName.equalsIgnoreCase(name.trim()));
    }
}