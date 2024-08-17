package edu.uj.po.simulation.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static String configDir = "src/edu/uj/po/simulation/config.properties";
    public static Properties loadProperties() {
        try {
            FileInputStream input = new FileInputStream(configDir);
            Properties prop = new Properties();
            prop.load(input);
            System.out.println("Loaded properties to app memory.");
            return prop;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
