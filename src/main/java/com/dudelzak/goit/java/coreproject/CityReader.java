package com.dudelzak.goit.java.coreproject;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
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
                throw new IllegalStateException("Cities dataset resource not found: " + resourcePath);
            }

            try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                Type listType = new TypeToken<List<String>>() {}.getType();
                List<String> list = gson.fromJson(reader, listType);
                if(list == null || list.isEmpty()) {
                    throw new IllegalStateException("Cities dataset in " + resourcePath + " is null or empty");
                }
                return list;
            }
        } catch (IOException | JsonParseException e) {
            throw new IllegalStateException("Failed to load or parse cities resource (" + resourcePath + "): " + e.getMessage(), e);
        }
    }
}
