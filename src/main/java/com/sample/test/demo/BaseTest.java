package com.sample.test.demo;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

  private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);
  protected String url;
  private Configuration config;
  private ThreadLocal<WebDriver> driver;


  @BeforeMethod(alwaysRun = true)
  public void init() {
    driver = new ThreadLocal<>();
    config = new Configuration();
    url = config.getUrl();
    initializeDriver(driver);
    navigateToSite();
  }

  public WebDriver getDriver() {
    return this.driver.get();
  }

  private void navigateToSite() {
    getDriver().get(url);
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    try {
      getDriver().quit();
      driver.remove();
      LOGGER.info("Browser successfully closed.");
    } catch (Exception e) {
      LOGGER.info("We got an error while closing browser. Here is the message: " + e.getMessage());
    }
  }

  private void initializeDriver(ThreadLocal<WebDriver> driver) {
    if (config.getBrowser().equalsIgnoreCase("chrome")) {
      if (config.getPlatform().equalsIgnoreCase("mac")) {
        System.setProperty("webdriver.chrome.driver",
            "src/test/resources/chromedriver/mac/chromedriver");
      } else {
        System.setProperty("webdriver.chrome.driver",
            "src/test/resources/chromedriver/windows/chromedriver.exe");
      }

      driver.set(new ChromeDriver());
      driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      LOGGER.info("Driver successfully created.");
    } else {
      LOGGER.info("Unsupported browser, unable to create.");
      fail("Unsupported browser " + config.getBrowser());
    }

  }
}
