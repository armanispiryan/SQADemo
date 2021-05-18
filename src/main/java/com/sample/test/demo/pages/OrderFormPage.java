package com.sample.test.demo.pages;

import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class OrderFormPage extends BasePage {

  public static final String SMALL_6_SLICES_NO_TOPPINGS = "Thank you for your order! TOTAL: 6.75 Small 6 Slices - no toppings";
  @FindBy(id = "pizza1Pizza")
  private WebElement pizza1;
  @FindBy(xpath = "//div[@id='pizza1']//select[@class='toppings1']")
  private WebElement pizza1Toppings1;
  @FindBy(xpath = "//div[@id='pizza1']//select[@class='toppings2']")
  private WebElement pizza1Toppings2;
  @FindBy(id = "pizza1Qty")
  private WebElement pizza1Quantity;
  @FindBy(id = "pizza1Cost")
  private WebElement pizza1Cost;
  @FindBy(id = "name")
  private WebElement orderOwnerName;
  @FindBy(id = "email")
  private WebElement orderOwnerEmail;
  @FindBy(id = "phone")
  private WebElement orderOwnerPhone;
  @FindBy(id = "ccpayment")
  private WebElement radioCreditCard;
  @FindBy(id = "cashpayment")
  private WebElement radioCash;
  @FindBy(id = "placeOrder")
  private WebElement placeOrderButton;
  @FindBy(id = "reset")
  private WebElement resetButton;
  @FindBy(id = "dialog")
  private WebElement dialog;
  @FindBy(xpath = "//div[@id='dialog']/p")
  private WebElement dialogText;

  public OrderFormPage(WebDriver webDriver) {
    super(webDriver);
  }

  public void choosePizzaType(PizzaTypes pizzaType) {
    Select pizzaTypes = new Select(pizza1);
    pizzaTypes.selectByValue(pizzaType.getDisplayName());
  }

  public String getPizzaType() {
    Select pizzaTypes = new Select(pizza1);
    return pizzaTypes.getFirstSelectedOption().getText();
  }

  public void chooseFirstToppings(PizzaToppings pizzaTopping) {
    Select pizzaToppings = new Select(pizza1Toppings1);
    pizzaToppings.selectByValue(pizzaTopping.getDisplayName());
  }

  public String getFirstToppings() {
    Select pizzaToppings = new Select(pizza1Toppings1);
    return pizzaToppings.getFirstSelectedOption().getText();
  }

  public void chooseSecondToppings(PizzaToppings pizzaTopping) {
    Select pizzaToppings = new Select(pizza1Toppings2);
    pizzaToppings.selectByValue(pizzaTopping.getDisplayName());
  }

  public void fillQuantity(int quantity) {
    this.pizza1Quantity.clear();
    this.pizza1Quantity.sendKeys("" + quantity);
  }

  public int getQuantity() {
    return Integer.parseInt(this.pizza1Quantity.getAttribute("value"));
  }

  public int getCost() {
    return Integer.parseInt(this.pizza1Quantity.getAttribute("value"));
  }

  public void fillName(String name) {
    this.orderOwnerName.clear();
    this.orderOwnerName.sendKeys(name);
  }

  public String getName() {
    return this.orderOwnerName.getText();
  }

  public void fillEmail(String email) {
    this.orderOwnerEmail.clear();
    this.orderOwnerEmail.sendKeys(email);
  }

  public String getEmail() {
    return this.orderOwnerEmail.getText();
  }

  public void fillPhone(String phone) {
    this.orderOwnerPhone.clear();
    this.orderOwnerPhone.sendKeys(phone);
  }

  public String getPhone() {
    return this.orderOwnerPhone.getText();
  }

  public void choosePaymentMethodAsCreditCard() {
    radioCreditCard.click();
  }

  public boolean isSelectedCreditCard() {
    return radioCreditCard.isSelected();
  }

  public void choosePaymentMethodAsCash() {
    radioCash.click();
  }

  public boolean isSelectedCash() {
    return radioCash.isSelected();
  }


  public void clickPlaceOrderButton() {
    placeOrderButton.click();
  }

  public void clickResetButton() {
    resetButton.click();
  }

  public String getDialogText() {
    waitForElementToAppear(dialog);
    return dialogText.getText();
  }
}
