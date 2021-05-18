package com.sample.test.demo.listeners;

import com.sample.test.demo.BaseTest;
import java.io.File;
import java.io.IOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {

  private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

  @Override
  public void onTestStart(ITestResult result) {
    LOGGER.info("The test started.");
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    LOGGER.info("The test succeed.");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    LOGGER.info("The test failed.");
    Object currentClass = result.getInstance();
    WebDriver webDriver = ((BaseTest) currentClass).getDriver();
    try {
      File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
      String filePathRoot = "src/test/resources/screenshots/";
      String fullFilePath = filePathRoot + result.getName() + ".jpg";
      FileUtils.copyFile(screenshot, new File(fullFilePath));
    } catch (IOException ex) {
      LOGGER.info("We got an error while creating screenshot: " + ex.getMessage());
    }

  }

  @Override
  public void onTestSkipped(ITestResult result) {
    LOGGER.info("The test Skipped.");
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    LOGGER.info("The test partially failed.");
  }

  @Override
  public void onStart(ITestContext context) {
    LOGGER.info("The test started.");
  }

  @Override
  public void onFinish(ITestContext context) {
    LOGGER.info("The test finished.");
  }
}
