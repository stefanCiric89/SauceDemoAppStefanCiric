import com.saucedemo.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyProductPriceBeforeOrderFinish extends BaseTest{

    @Test
    public void TotalPriceNoTax() throws InterruptedException {

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

       /*List<WebElement> cartList = webDriver.findElements(By.className("cart_item"));
        double firstItemPrice = Double.parseDouble(cartList.get(0).findElement(By.className("inventory_item_price")).getText().substring(1));
        double secondItemPrice = Double.parseDouble(cartList.get(1).findElement(By.className("inventory_item_price")).getText().substring(1));
        double totalPriceNoTax = firstItemPrice + secondItemPrice;
        WebElement summaryInfo = webDriver.findElement(By.className("summary_subtotal_label"));
        double summaryItemTotal = Double.parseDouble(summaryInfo.getText().substring(13));
        Assert.assertEquals(totalPriceNoTax, summaryItemTotal,"Price is not same as Item total price! :-(");
        System.out.println("Total price of added products is $" + totalPriceNoTax + " " + "Total price of the added products without taxes is $" + summaryItemTotal);*/

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(webDriver);

        double firstPrice = checkoutOverviewPage.getItemPriceOnIndex(0);
        double secondPrice = checkoutOverviewPage.getItemPriceOnIndex(1);
        double totalPriceNoTax = firstPrice + secondPrice;
        double subtotalItemPrice = checkoutOverviewPage.getSubtotalItemPrice();

        Assert.assertEquals(totalPriceNoTax, subtotalItemPrice,"Price is not same as Item total price! :-(");
        System.out.println("Total price of added products is $" + totalPriceNoTax + " " + "Total price of the added products without taxes is $" + subtotalItemPrice);
        checkoutOverviewPage.clickCancelBtn();
        Thread.sleep(1000);
        productPage.eliminateProductFromProductPageByName("Sauce Labs Backpack");
        productPage.eliminateProductFromProductPageByName("Sauce Labs Bike Light");
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
        yourCartPage.clickCheckoutBtn();


        CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage(webDriver);
        checkoutYourInformationPage.setInpYourFirstName("Stefan");
        checkoutYourInformationPage.setInpYourLastName("Ciric");
        checkoutYourInformationPage.setInpYourZipCode("18000");
        checkoutYourInformationPage.clickContinueBtn();

        /*List<WebElement> cartList = webDriver.findElements(By.className("cart_item"));
        double firstItemPrice = Double.parseDouble(cartList.get(0).findElement(By.className("inventory_item_price")).getText().substring(1));
        double secondItemPrice = Double.parseDouble(cartList.get(1).findElement(By.className("inventory_item_price")).getText().substring(1));
        double totalPriceNoTax = firstItemPrice + secondItemPrice;
        WebElement summaryInfoTax = webDriver.findElement(By.className("summary_tax_label"));
        double summaryTax = Double.parseDouble(summaryInfoTax.getText().substring(6)); */

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(webDriver);

        double firstPrice = checkoutOverviewPage.getItemPriceOnIndex(0);
        double secondPrice = checkoutOverviewPage.getItemPriceOnIndex(1);
        double totalPriceNoTax = firstPrice + secondPrice;
        double taxOnGoods = checkoutOverviewPage.getTaxOnGoods();
        double totalItemPrice = totalPriceNoTax + taxOnGoods;
        double totalProductPrice = checkoutOverviewPage.getTotalProductPrice();


        Assert.assertEquals(totalProductPrice, totalItemPrice,"Price is not same as Total Product Price! :-(");
        System.out.println("Actual total price of added products with taxes is $" + totalItemPrice + " " + "Expected total price of the added products with taxes is $" + totalProductPrice);
    }
}
