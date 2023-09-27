package com.foofinc.MeCS.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProps {
    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = AppProps.class.getClassLoader()
                                                     .getResourceAsStream("application.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBearerToken() {
        return properties.getProperty("app.bearer-token");
    }
}
