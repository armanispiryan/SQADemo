package com.sample.test.demo.tests;

import static com.sample.test.demo.constants.PizzaToppings.DEFAULT_TOPPING;
import static com.sample.test.demo.constants.PizzaToppings.OLIVES;
import static com.sample.test.demo.constants.PizzaToppings.SALAMI;
import static com.sample.test.demo.constants.PizzaTypes.DEFAULT_PIZZA;
import static com.sample.test.demo.constants.PizzaTypes.LARGE_THREETOPPINGS;
import static com.sample.test.demo.constants.PizzaTypes.SMALL_NOTOPPINGS;

import com.sample.test.demo.BaseTest;
import com.sample.test.demo.listeners.TestListener;
import com.sample.test.demo.pages.OrderFormPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(TestListener.class)
public class DemoTest extends BaseTest {

  SoftAssert softAssert = new SoftAssert();

  @Test(testName = "TC-PO-001 Verifying order with minimum required fields")
  public void happyPathMinValuesTest() {
    OrderFormPage orderFormPage = new OrderFormPage(getDriver());
    orderFormPage.choosePizzaType(SMALL_NOTOPPINGS);
    orderFormPage.fillQuantity(1);
    orderFormPage.fillName("testName");
    orderFormPage.fillPhone("+123456");
    orderFormPage.choosePaymentMethodAsCash();
    orderFormPage.clickPlaceOrderButton();
    Assert.assertEquals(
        orderFormPage.getDialogText(),
        OrderFormPage.SMALL_6_SLICES_NO_TOPPINGS,
        "We have differance please see reports for more details.");
  }

  @Test(testName = "TC-PO-005 verify reset button.")
  public void errorCase() {
    OrderFormPage orderFormPage = new OrderFormPage(getDriver());
    orderFormPage.choosePizzaType(LARGE_THREETOPPINGS);
    orderFormPage.chooseFirstToppings(OLIVES);
    orderFormPage.chooseSecondToppings(SALAMI);
    orderFormPage.fillQuantity(1);
    orderFormPage.fillName("testName");
    orderFormPage.fillEmail("testEmail@gmail.com");
    orderFormPage.fillPhone("+123456");
    orderFormPage.choosePaymentMethodAsCash();
    orderFormPage.clickResetButton();

    softAssert.assertEquals(orderFormPage.getPizzaType(), DEFAULT_PIZZA.getDisplayName(),
        "Pizza field doesn't reset!");
    softAssert
        .assertEquals(orderFormPage.getFirstToppings(), DEFAULT_TOPPING.getDisplayName() + " 1",
            "Toppings 1 field doesn't reset!");
    softAssert
        .assertEquals(orderFormPage.getFirstToppings(), DEFAULT_TOPPING.getDisplayName() + " 2",
            "Toppings 2 field doesn't reset!");
    softAssert.assertEquals(orderFormPage.getQuantity(), 0,
        "Quantity field doesn't reset!");
    softAssert.assertEquals(orderFormPage.getCost(), 0,
        "Cost field doesn't reset!");
    softAssert.assertEquals(orderFormPage.getCost(), 0,
        "Cost field doesn't reset!");
    softAssert.assertEquals(orderFormPage.getName(), "",
        "Name field doesn't reset!");
    softAssert.assertEquals(orderFormPage.getEmail(), "",
        "Email field doesn't reset!");
    softAssert.assertEquals(orderFormPage.getPhone(), "",
        "Phone field doesn't reset!");
    softAssert.assertEquals(orderFormPage.isSelectedCash(), false,
        "Cash field doesn't reset!");

    softAssert.assertAll();
  }

}
