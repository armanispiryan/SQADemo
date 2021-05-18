package com.sample.test.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

  private static final int TIMEOUT = 5;
  private static final int POLLING = 100;
  private final WebDriverWait wait;
  protected WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(this.driver, TIMEOUT, POLLING);
    PageFactory.initElements(driver, this);
  }

  protected void waitForElementToAppear(WebElement webElement) {
    wait.until(ExpectedConditions.visibilityOf(webElement));
  }
}
