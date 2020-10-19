package ru.stormnet.yandex.utills;

import java.io.*;
import java.util.Properties;

public class PropertiesManager {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream("src\\test\\resources\\properties\\testData.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
