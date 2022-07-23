import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductPage;
import com.saucedemo.provider.InvalidUserNameProvider;
import com.saucedemo.provider.UserNameProvider;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class VerifyLoginTest extends BaseTest {

    @Test(dataProvider = "UserNameProvider", dataProviderClass = UserNameProvider.class)
    public void VerifyLoginWithValidCredentials(String userName) {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        loginPage.setInpUserName(userName);
        loginPage.setInpPassword("secret_sauce");
        loginPage.clickOnLoginBtn();

        ProductPage productPage = new ProductPage(webDriver);
        Assert.assertEquals(productPage.isProductPageDisplayed(),true,"Login failed! :-(");
    }

    @Test(dataProvider = "InvalidUserNameProvider", dataProviderClass = InvalidUserNameProvider.class)
    public void VerifyLoginWithInvalidUsernameCredentials(String invalidUserName) {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        loginPage.setInpUserName(invalidUserName);
        loginPage.setInpPassword("secret_sauce");
        loginPage.clickOnLoginBtn();
        String actualErrorMessage = loginPage.getErrorMessage().getText();


        ProductPage productPage = new ProductPage(webDriver);
        Assert.assertEquals(productPage.isProductPageDisplayed(),false,"Login failed! :-(");
        Assert.assertEquals(actualErrorMessage,"Epic sadface: Username and password do not match any user in this service", "Login failed! :-(");
    }

    @Test(dataProvider = "UserNameProvider", dataProviderClass = UserNameProvider.class)
    public void VerifyLoginWithInvalidPassword(String userName) {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        loginPage.setInpUserName(userName);
        loginPage.setInpPassword("password_qwerty");
        loginPage.clickOnLoginBtn();
        String actualErrorMessage = loginPage.getErrorMessage().getText();

        ProductPage productPage = new ProductPage(webDriver);
        Assert.assertEquals(productPage.isProductPageDisplayed(),false,"Login failed! :-(");
        Assert.assertEquals(actualErrorMessage,"Epic sadface: Username and password do not match any user in this service", "Login failed! :-(");
    }

    @Test
    public void VerifyLoginWithoutUserNameAndValidPassword() {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        loginPage.setInpUserName("");
        loginPage.setInpPassword("secret_sauce");
        loginPage.clickOnLoginBtn();
        String actualErrorMessage = loginPage.getErrorMessage().getText();

        ProductPage productPage = new ProductPage(webDriver);
        Assert.assertEquals(productPage.isProductPageDisplayed(),false,"Login failed! :-(");
        Assert.assertEquals(actualErrorMessage,"Epic sadface: Username is required", "Login failed! :-(");
    }

    @Test(dataProvider = "UserNameProvider", dataProviderClass = UserNameProvider.class)
    public void VerifyLoginWithValidUserNameAndWithoutPassword(String userName) {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        loginPage.setInpUserName(userName);
        loginPage.setInpPassword("");
        loginPage.clickOnLoginBtn();
        String actualErrorMessage = loginPage.getErrorMessage().getText();


        ProductPage productPage = new ProductPage(webDriver);
        Assert.assertEquals(productPage.isProductPageDisplayed(),false,"Login failed! :-(");
        Assert.assertEquals(actualErrorMessage,"Epic sadface: Password is required", "Login failed! :-(");
    }

    @Test
    public void VerifyLoginWithoutUserNameAndWithoutPassword() {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        loginPage.setInpUserName("");
        loginPage.setInpPassword("");
        loginPage.clickOnLoginBtn();
        String actualErrorMessage = loginPage.getErrorMessage().getText();

        ProductPage productPage = new ProductPage(webDriver);
        Assert.assertEquals(productPage.isProductPageDisplayed(),false,"Login failed! :-(");
        Assert.assertEquals(actualErrorMessage,"Epic sadface: Username is required", "Login failed! :-(");
    }

}
