package com.sample.test.demo;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Configuration {

  private static final Logger LOGGER = LogManager.getLogger(Configuration.class);
  private static final String CONFIG_FILE_NAME = "config.properties";
  private Properties configProperties;


  public Configuration() {
    loadProperties();
  }

  private void loadProperties() {
    configProperties = new Properties();
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    assertNotNull(classLoader);
    InputStream inputStream = classLoader.getResourceAsStream(CONFIG_FILE_NAME);
    try {
      configProperties.load(inputStream);
      LOGGER.info("Configuration is loaded successfully.");
    } catch (final IOException e) {
      LOGGER.info(
          "We got an issue while loading configuration, here is the message: " + e.getMessage());
    }
  }

  public String getBrowser() {
    return getProperty("browser");
  }

  public String getPlatform() {
    return getProperty("platform");
  }

  public String getUrl() {
    return getProperty("url");
  }

  public String getProperty(String propertyName) {
    return configProperties.getProperty(propertyName);
  }
}
