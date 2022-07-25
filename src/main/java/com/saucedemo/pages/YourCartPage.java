package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class YourCartPage extends BasePage {

    public YourCartPage(WebDriver webDriver) {
        super(webDriver);
        this.url = super.url + "cart.html";
    }

    public void openPage() {
        webDriver.get(this.url);
        webDriver.manage().window().maximize();
    }

    public void eliminateProductFromYourCartByName(String productName) {

        WebElement listCartContentContainer = webDriver.findElement(By.className("cart_contents_container"));
        List<WebElement> cartItem = listCartContentContainer.findElements(By.className("cart_item"));

        Integer indexElement = 0;
        for (int i = 0; i < cartItem.size(); i++) {
            WebElement inventoryItemName = cartItem.get(i).findElement(By.className("inventory_item_name"));
            if (inventoryItemName.getText().equals(productName)) {
                indexElement = i;
                break;
            }
        }
        WebElement removeButton = cartItem.get(indexElement).findElement(By.xpath(".//button"));
        removeButton.click();
    }


    public Boolean isYourCartPageDisplayed() {

        Boolean toReturn = true;

        List<WebElement> listInventoryContainer = webDriver.findElements(By.id("cart_contents_container"));

        if (listInventoryContainer.size() == 1) {
            toReturn = true;
        } else {
            toReturn = false;
        }
        return toReturn;
    }


    public Boolean isYourCartPageEmpty() {

        Boolean toReturn = true;

        List<WebElement> cartItem = webDriver.findElements(By.className("cart_item"));

        if (cartItem.size() == 0) {
            toReturn = true;
        } else {
            toReturn = false;
        }
        return toReturn;
    }


    private WebElement getCheckoutBtn() {
        return webDriver.findElement(By.id("checkout"));
    }

    public void clickCheckoutBtn() {
        this.getCheckoutBtn().click();
    }

    public WebElement getCheapestProductName() {
        return webDriver.findElement(By.xpath("//div[@class='inventory_item_name']"));
    }

    public WebElement getFirstProductName() {
        return webDriver.findElement(By.xpath("//div[@class='inventory_item_name']"));
    }
}
