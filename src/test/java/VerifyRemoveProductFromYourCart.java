import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductPage;
import com.saucedemo.pages.YourCartPage;
import com.saucedemo.provider.ProductNameProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyRemoveProductFromYourCart extends BaseTest {

    @Test(dataProvider = "ProductNameProvider", dataProviderClass = ProductNameProvider.class)
    public void VerifyAddProductToCartByProductName(String productName) throws InterruptedException {

        LoginPage loginPage = new LoginPage(webDriver);

        loginPage.openPage();
        loginPage.setInpUserName("standard_user");
        loginPage.setInpPassword("secret_sauce");
        loginPage.clickOnLoginBtn();

        ProductPage productPage = new ProductPage(webDriver);
        Assert.assertEquals(productPage.isProductPageDisplayed(), true, "Login failed! :-(");
        //productPage.addProductToCartByName("Sauce Labs Backpack");
        //productPage.addProductToCartByName("Sauce Labs Bolt T-Shirt");
        productPage.addProductToCartByName(productName);
        Thread.sleep(2000);
        productPage.clickCartBtn();

        YourCartPage yourCartPage = new YourCartPage(webDriver);
        Assert.assertEquals(yourCartPage.isYourCartPageDisplayed(), true, "Login failed! :-(");
        Thread.sleep(2000);
        yourCartPage.eliminateProductFromYourCartByName("Sauce Labs Backpack");
        //yourCartPage.eliminateProductFromYourCartByName("Sauce Labs Bolt T-Shirt");
        Assert.assertEquals(yourCartPage.isYourCartPageEmpty(),true, "Your shopping cart is not empty");
    }
}
