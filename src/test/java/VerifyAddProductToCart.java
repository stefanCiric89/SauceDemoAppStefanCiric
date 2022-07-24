import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductPage;
import com.saucedemo.pages.YourCartPage;
import com.saucedemo.provider.ProductNameProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class VerifyAddProductToCart extends BaseTest {

    @Test
    public void VerifyAddProductToCartByProductName() {

        LoginPage loginPage = new LoginPage(webDriver);

        loginPage.openPage();
        loginPage.setInpUserName("standard_user");
        loginPage.setInpPassword("secret_sauce");
        loginPage.clickOnLoginBtn();

        ProductPage productPage = new ProductPage(webDriver);
        Assert.assertEquals(productPage.isProductPageDisplayed(), true, "Login failed! :-(");

        productPage.selectZToA();
        productPage.addProductToCartByName("Sauce Labs Backpack");
        productPage.addProductToCartByName("Sauce Labs Bolt T-Shirt");
        productPage.addProductToCartByName("Sauce Labs Bike Light");
        productPage.addProductToCartByName("Sauce Labs Fleece Jacket");
        productPage.addProductToCartByName("Sauce Labs Onesie");
        productPage.addProductToCartByName("Test.allTheThings() T-Shirt (Red)");
        productPage.clickCartBtn();

        YourCartPage yourCartPage = new YourCartPage(webDriver);
        Assert.assertEquals(yourCartPage.isYourCartPageDisplayed(), true, "Login failed! :-(");


        WebElement shoppingCartContainer = webDriver.findElement(By.id("shopping_cart_container"));
        List<WebElement> shoppingCartBadge = shoppingCartContainer.findElements(By.xpath(".//span"));
        Integer numOfProductsBeforeAdd;
        if(shoppingCartBadge.size() > 0) {
            numOfProductsBeforeAdd = 0;
        } else {
            numOfProductsBeforeAdd = Integer.parseInt(shoppingCartBadge.get(0).getText());
        }
        Assert.assertEquals(Integer.parseInt(shoppingCartBadge.get(0).getText()), numOfProductsBeforeAdd + 5);
    }

    @Test
    public void VerifyAddCheapestProductToCart_withSortLoHi() throws InterruptedException {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        loginPage.setInpUserName("standard_user");
        loginPage.setInpPassword("secret_sauce");
        loginPage.clickOnLoginBtn();

        ProductPage productPage = new ProductPage(webDriver);
        Assert.assertEquals(productPage.isProductPageDisplayed(), true, "Login failed! :-(");
        productPage.selectLowToHigh();
        Thread.sleep(2000);
        productPage.getFirstProductFromList();

        YourCartPage yourCartPage = new YourCartPage(webDriver);
        yourCartPage.openPage();
        String actualFirstProductNameFromList = yourCartPage.getFirstProductName().getText();
        Assert.assertEquals(actualFirstProductNameFromList,"Sauce Labs Onesie","Your cart is empty! :-(");
        yourCartPage.eliminateProductFromYourCartByName("Sauce Labs Onesie");

    }

    @Test
    public void VerifyAddCheapestProductToCart_withoutSort() throws InterruptedException {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        loginPage.setInpUserName("standard_user");
        loginPage.setInpPassword("secret_sauce");
        loginPage.clickOnLoginBtn();

        ProductPage productPage = new ProductPage(webDriver);
        productPage.openPage();
        Assert.assertEquals(productPage.isProductPageDisplayed(), true, "Login failed! :-(");
        Thread.sleep(1000);
        productPage.addCheapestProduct(productPage.cheapestProduct());

        YourCartPage yourCartPage = new YourCartPage(webDriver);
        yourCartPage.openPage();
        String actualCheapestProductName = yourCartPage.getCheapestProductName().getText();
        Assert.assertEquals(actualCheapestProductName,"Sauce Labs Onesie","Your cart is empty! :-(");
        yourCartPage.eliminateProductFromYourCartByName("Sauce Labs Onesie");
    }
}
