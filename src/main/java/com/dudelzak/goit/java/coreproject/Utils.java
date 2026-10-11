package com.dudelzak.goit.java.coreproject;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Utils {
    public static Image getIcon() {
        URL url = Utils.class.getResource("/icon.png");
        if (url == null) {
            throw new IllegalArgumentException("Файл /icon.png не найден");
        }
        return new ImageIcon(url).getImage();
    }
}