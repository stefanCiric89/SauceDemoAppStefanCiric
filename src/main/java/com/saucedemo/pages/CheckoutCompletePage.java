package com.saucedemo.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CheckoutCompletePage extends BasePage {

    public CheckoutCompletePage(WebDriver webDriver) {
        super(webDriver);
        this.url = super.url + "checkout-complete.html";
    }

    public Boolean isCheckoutPageDisplayed() {
        Boolean toReturn = true;

        List<WebElement> listCheckoutCompleteContainer = webDriver.findElements(By.id("checkout_complete_container"));

        if (listCheckoutCompleteContainer.size() == 1) {
            toReturn = true;
        } else {
            toReturn = false;
        }
        return toReturn;
    }

    public WebElement getCheckoutCompleteMessage() {
        return webDriver.findElement(By.xpath("//div[@class='complete-text']"));
    }
}
