package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage(WebDriver webDriver) {
        super(webDriver);
        this.url = super.url + "checkout-step-two.html";
    }


    private WebElement getCartFooterBtn() {
        return webDriver.findElement(By.id("finish"));
    }

    public void clickFinishBtn() {
        this.getCartFooterBtn().click();
    }
}