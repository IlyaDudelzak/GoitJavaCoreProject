package com.dudelzak.goit.java.coreproject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CityReader {
    private static final Gson gson = new Gson();

    public static List<String> getCities() {
        String resourcePath = "cities.json";

        try (InputStream is = CityReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalArgumentException("Файл не найден: " + resourcePath);
            }

            try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                Type listType = new TypeToken<List<String>>() {}.getType();
                return gson.fromJson(reader, listType);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
