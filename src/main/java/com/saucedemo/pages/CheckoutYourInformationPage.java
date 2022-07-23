package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutYourInformationPage extends BasePage {

    private WebElement inpYourFirstName;
    private WebElement inpYourLastName;
    private WebElement inpYourZipCode;
    private  String url;



    public CheckoutYourInformationPage(WebDriver webDriver) {
        super(webDriver);
        this.url = super.url + "checkout-step-one.html";
    }


    private WebElement getFirstNamePlaceholder() {
        return webDriver.findElement(By.id("first-name"));
    }

    public void setInpYourFirstName (String firstName) {
        this.getFirstNamePlaceholder().sendKeys(firstName);
    }

    private WebElement getLastNamePlaceholder() {
        return webDriver.findElement(By.id("last-name"));
    }

    public void setInpYourLastName (String lastName) {
        this.getLastNamePlaceholder().sendKeys(lastName);
    }

    private WebElement getZipCodePlaceholder() {
        return webDriver.findElement(By.id("postal-code"));
    }

    public void setInpYourZipCode (String yourZipCode) {
        this.getZipCodePlaceholder().sendKeys(yourZipCode);
    }

    private WebElement getCheckoutBtn() {
        return webDriver.findElement(By.id("continue"));
    }

    public void clickContinueBtn() {
        this.getCheckoutBtn().click();
    }
}