package com.amwell.auto.yahav.sample.pom.core.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class AWMapper {

    private static final String ADMIN_FILE_LOCATORS = System.getProperty("user.dir") + "/resources/properties/admin.properties";
    private static final String PROVIDER_FILE_LOCATORS = System.getProperty("user.dir") + "/resources/properties/provider.properties";
    private static final String CONSUMER_FILE_LOCATORS = System.getProperty("user.dir") + "/resources/properties/consumer.properties";
    private static final String STAFF_FILE_LOCATORS = System.getProperty("user.dir") + "/resources/properties/staff.properties";
    private static final String RECOVER_FILE_LOCATORS = System.getProperty("user.dir") + "/resources/properties/recover.properties";
    private static final String TEST_DATA = System.getProperty("user.dir") + "/resources/properties/test_data.properties";
    private static final String CONFIG_DATA = System.getProperty("user.dir") + "/resources/properties/config_data.properties";

    static Properties properties = new Properties();

    /**
     * getData method identify property file and key to return a locator
     * of WebElement from a given property file name
     * @param fileType - the type of property file
     * @param key - a key in the property file
     * @return - result of getData as String locator
     */
    public static String getData(String fileType, String key) {
        try {
            switch (fileType.toLowerCase()) {
                case "admin":
                    FileInputStream adminFile = new FileInputStream(ADMIN_FILE_LOCATORS);
                    properties.load(adminFile);
                    return getData(key);
                case "provider":
                    FileInputStream providerFile = new FileInputStream(PROVIDER_FILE_LOCATORS);
                    properties.load(providerFile);
                    return getData(key);
                case "consumer":
                    FileInputStream consumerFile = new FileInputStream(CONSUMER_FILE_LOCATORS);
                    properties.load(consumerFile);
                    return getData(key);
                case "staff":
                    FileInputStream staffFile = new FileInputStream(STAFF_FILE_LOCATORS);
                    properties.load(staffFile);
                    return getData(key);
                case "recover":
                    FileInputStream recoverFile = new FileInputStream(RECOVER_FILE_LOCATORS);
                    properties.load(recoverFile);
                    return getData(key);
                case "test_data":
                    FileInputStream testDataFile = new FileInputStream(TEST_DATA);
                    properties.load(testDataFile);
                    return getData(key);
                case "config_data":
                    FileInputStream configDataFile = new FileInputStream(CONFIG_DATA);
                    properties.load(configDataFile);
                    return getData(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Could not read from file : " + fileType + " due to Error -> " + e.getCause();
        }
        return null;
    }

    /**
     * getData returns the locator value of WebElement from a property file
     * @param key - String object that represents a key inside a property file
     * @return - the locator that paired to the value of given key
     */
    private static String getData(String key) {
        final String value = properties.getProperty(key);
        return value;
    }

}

