import com.saucedemo.pages.CheckoutYourInformationPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductPage;
import com.saucedemo.pages.YourCartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class VerifyProductPriceBeforeOrderFinish extends BaseTest{

    @Test
    public void TotalPriceNoTax(){

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        loginPage.setInpUserName("standard_user");
        loginPage.setInpPassword("secret_sauce");
        loginPage.clickOnLoginBtn();

        ProductPage productPage = new ProductPage(webDriver);
        productPage.addProductToCartByName("Sauce Labs Backpack");
        productPage.addProductToCartByName("Sauce Labs Bike Light");
        productPage.clickCartBtn();

        YourCartPage yourCartPage = new YourCartPage(webDriver);
        yourCartPage.clickCheckoutBtn();

        CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage(webDriver);
        checkoutYourInformationPage.setInpYourFirstName("Stefan");
        checkoutYourInformationPage.setInpYourLastName("Ciric");
        checkoutYourInformationPage.setInpYourZipCode("18000");
        checkoutYourInformationPage.clickContinueBtn();

        List<WebElement> cartList = webDriver.findElements(By.className("cart_item"));

        double firstItemPrice = Double.parseDouble(cartList.get(0).findElement(By.className("inventory_item_price")).getText().substring(1));
        double secondItemPrice = Double.parseDouble(cartList.get(1).findElement(By.className("inventory_item_price")).getText().substring(1));
        double totalPriceNoTax = firstItemPrice + secondItemPrice;

        WebElement summaryInfo = webDriver.findElement(By.className("summary_subtotal_label"));
        double summaryItemTotal = Double.parseDouble(summaryInfo.getText().substring(13));

        Assert.assertEquals(totalPriceNoTax, summaryItemTotal,"Price is not same as Item total price! :-(");
        System.out.println("Total price of added products is $" + totalPriceNoTax + " " + "Total price of the added products without taxes is $" + summaryItemTotal);
    }

    @Test
    public void TotalPriceWithTax() {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        loginPage.setInpUserName("standard_user");
        loginPage.setInpPassword("secret_sauce");
        loginPage.clickOnLoginBtn();

        ProductPage productPage = new ProductPage(webDriver);
        productPage.addProductToCartByName("Sauce Labs Fleece Jacket");
        productPage.addProductToCartByName("Test.allTheThings() T-Shirt (Red)");
        productPage.clickCartBtn();

        YourCartPage yourCartPage = new YourCartPage(webDriver);
        yourCartPage.eliminateProductFromYourCartByName("Sauce Labs Backpack");
        yourCartPage.eliminateProductFromYourCartByName("Sauce Labs Bike Light");
        yourCartPage.clickCheckoutBtn();


        CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage(webDriver);
        checkoutYourInformationPage.setInpYourFirstName("Stefan");
        checkoutYourInformationPage.setInpYourLastName("Ciric");
        checkoutYourInformationPage.setInpYourZipCode("18000");
        checkoutYourInformationPage.clickContinueBtn();

        List<WebElement> cartList = webDriver.findElements(By.className("cart_item"));

        double firstItemPrice = Double.parseDouble(cartList.get(0).findElement(By.className("inventory_item_price")).getText().substring(1));
        double secondItemPrice = Double.parseDouble(cartList.get(1).findElement(By.className("inventory_item_price")).getText().substring(1));
        double totalPriceNoTax = firstItemPrice + secondItemPrice;

        WebElement summaryInfoTax = webDriver.findElement(By.className("summary_tax_label"));
        double summaryTax = Double.parseDouble(summaryInfoTax.getText().substring(6));

        WebElement summaryInfoTotal = webDriver.findElement(By.className("summary_total_label"));
        double summaryTotal = Double.parseDouble(summaryInfoTotal.getText().substring(8));
        double totalProductPrice = totalPriceNoTax + summaryTax;


        Assert.assertEquals(totalProductPrice, summaryTotal,"Price is not same as Item total price! :-(");
        System.out.println("Total price of added products is $" + totalProductPrice + " " + "Total price of the added products with taxes is $" + totalProductPrice);
    }
}
