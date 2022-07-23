import com.saucedemo.pages.*;
import org.openqa.selenium.devtools.v85.log.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifySuccessfulPurchase extends BaseTest {

    @Test
    public void VerifySuccessfulPurchase(){

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        loginPage.setInpUserName("standard_user");
        loginPage.setInpPassword("secret_sauce");
        loginPage.clickOnLoginBtn();

        ProductPage productPage = new ProductPage(webDriver);
        productPage.addProductToCartByName("Sauce Labs Backpack");
        productPage.addProductToCartByName("Test.allTheThings() T-Shirt (Red)");
        productPage.clickCartBtn();

        YourCartPage yourCartPage = new YourCartPage(webDriver);
        yourCartPage.clickCheckoutBtn();

        CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage(webDriver);
        checkoutYourInformationPage.setInpYourFirstName("Stefan");
        checkoutYourInformationPage.setInpYourLastName("Ciric");
        checkoutYourInformationPage.setInpYourZipCode("18000");
        checkoutYourInformationPage.clickContinueBtn();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(webDriver);
        checkoutOverviewPage.clickFinishBtn();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(webDriver);
        String actualCompleteMessage = checkoutCompletePage.getCheckoutCompleteMessage().getText();
        Assert.assertEquals(actualCompleteMessage,"Your order has been dispatched, and will arrive just as fast as the pony can get there!","Your order s not complete!");
    }

}
