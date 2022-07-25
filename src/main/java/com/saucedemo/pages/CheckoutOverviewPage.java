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


    private WebElement getCartFooterFinishBtn() {
        return webDriver.findElement(By.id("finish"));
    }

    public void clickFinishBtn() {
        this.getCartFooterFinishBtn().click();
    }

    private WebElement getCartFooterCancelBtn() {
        return webDriver.findElement(By.id("cancel"));
    }

    public void clickCancelBtn() {
        this.getCartFooterCancelBtn().click();
    }




    public Double getItemPriceOnIndex(Integer index) {

        List<WebElement> cartList = webDriver.findElements(By.className("cart_item"));
        double itemPrice = Double.parseDouble(cartList.get(index).findElement(By.className("inventory_item_price")).getText().substring(1));
        return itemPrice;
    }

    public Double getSubtotalItemPrice() {

        WebElement summarySubtotalLabel = webDriver.findElement(By.className("summary_subtotal_label"));
        double summaryItemTotal = Double.parseDouble(summarySubtotalLabel.getText().substring(13));
        return summaryItemTotal;
    }

    public Double getTaxOnGoods() {
        WebElement summaryInfoTax = webDriver.findElement(By.className("summary_tax_label"));
        double taxOnGoods = Double.parseDouble(summaryInfoTax.getText().substring(6));
        return taxOnGoods;
    }

    public Double getTotalProductPrice() {

        WebElement summaryInfoTotal = webDriver.findElement(By.className("summary_total_label"));
        double summaryTotal = Double.parseDouble(summaryInfoTotal.getText().substring(8));
        return summaryTotal;
    }
}