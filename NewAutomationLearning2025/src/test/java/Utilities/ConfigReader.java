package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("C:\\Users\\Kamaljit_Singh\\Documents\\testng2025_new\\NewAutomationLearning2025\\src\\test\\java\\Utilities\\config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
