package com.dudelzak.goit.java.coreproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CityNameStorage {

    public static final List<String> STATIC_CITIES_EN = Arrays.asList(
            "New York", "London", "Tokyo", "Paris", "Berlin",
            "Rome", "Madrid", "Kyiv", "Toronto", "Sydney",
            "Seoul", "Dubai", "Singapore", "Amsterdam", "Vienna",
            "Prague", "Warsaw", "Rio de Janeiro", "Buenos Aires", "Cairo",
            "Athens", "Bangkok", "Beijing", "Brussels", "Budapest",
            "Copenhagen", "Dublin", "Helsinki", "Istanbul", "Lisbon",
            "Melbourne", "Mexico City", "Miami", "Milan", "Montreal",
            "Munich", "Nairobi", "Oslo", "Ottawa", "Stockholm",
            "Venice", "Washington", "Zurich", "Chicago", "Boston",
            "Los Angeles", "San Francisco", "Seattle", "Austin", "Denver",
            "Las Vegas", "Orlando", "Philadelphia", "Atlanta", "Houston",
            "Dallas", "Vancouver", "Calgary", "Auckland", "Wellington",
            "Geneva", "Hamburg", "Frankfurt", "Barcelona", "Valencia",
            "Seville", "Naples", "Florence", "Krakow", "Gdansk",
            "Bratislava", "Zagreb", "Belgrade", "Bucharest", "Sofia",
            "Tallinn", "Riga", "Vilnius", "Edinburgh", "Glasgow",
            "Marseille", "Lyon", "Nice", "Toulouse", "Bordeaux",
            "Antwerp", "Rotterdam", "Utrecht", "Cologne", "Stuttgart",
            "Dresden", "Leipzig", "Bologna", "Turin", "Verona",
            "Manchester", "Birmingham", "Leeds", "Liverpool", "Bristol",
            "Kyoto", "Osaka", "Yokohama", "Hiroshima", "Sapporo"
    );

    private final List<String> cityNamesList;


    public CityNameStorage() {
        this.cityNamesList = new ArrayList<>(STATIC_CITIES_EN);
    }

    public boolean isEmpty() {
        return cityNamesList.isEmpty();
    }

    public String getNameByFirstLetter(char firstLetter, boolean remove) {
        String searchPrefix = String.valueOf(firstLetter).toUpperCase();
        for (String cityName : cityNamesList) {
            if (cityName.toUpperCase().startsWith(searchPrefix)) {
                if (remove) {
                    cityNamesList.remove(cityName);
                }
                return cityName;
            }
        }
        return null;
    }

    public String getNameByPlayerMove(String playerMove, boolean remove) {
        playerMove = playerMove.trim();
        if (playerMove.isEmpty()) return null;

        int idx = playerMove.length() - 1;

        char lastChar = playerMove.charAt(idx);
        return getNameByFirstLetter(lastChar, remove);
    }

    public boolean containsName(String name) {
        for (String cityName : cityNamesList) {
            if (cityName.equalsIgnoreCase(name.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean removeCityName(String name) {
        return cityNamesList.removeIf(cityName -> cityName.equalsIgnoreCase(name.trim()));
    }

    public String getRandomCityAndRemove() {
        if (cityNamesList.isEmpty()) return null;
        int index = (int) (Math.random() * cityNamesList.size());
        return cityNamesList.remove(index);
    }
}