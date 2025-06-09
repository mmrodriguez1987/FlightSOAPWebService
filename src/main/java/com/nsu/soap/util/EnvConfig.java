package com.nsu.soap.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvConfig
{
    private static final Properties props = new Properties();

    static {
        try (InputStream input = EnvConfig.class.getClassLoader().getResourceAsStream("env.properties")) {
            if (input != null) {
                props.load(input);
            } else {
                throw new RuntimeException("env.properties not found");
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load env.properties", ex);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}