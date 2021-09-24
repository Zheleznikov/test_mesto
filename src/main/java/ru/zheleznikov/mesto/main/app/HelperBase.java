package ru.zheleznikov.mesto.main.app;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HelperBase {

    Properties properties;
    String pathToProperties = "src/main/resources/%s.properties";

    public HelperBase() throws IOException {
        properties = new Properties();
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format(pathToProperties, target)));
    }
}
