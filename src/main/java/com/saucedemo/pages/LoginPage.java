package com.saucedemo.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage extends BasePage {

    private WebElement inpUserName;
    private WebElement inpPassword;
    private WebElement btnLogin;
    private String url;



    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        this.url = super.url;
    }



    public WebElement getInpUserName() {
        return webDriver.findElement(By.id("user-name"));
    }
    public void setInpUserName(String userName) {
        this.getInpUserName().sendKeys(userName);
    }



    public WebElement getInpPassword(){
        return webDriver.findElement(By.id("password"));
    }
    public void setInpPassword(String password){
        this.getInpPassword().sendKeys(password);
    }



    public WebElement getBtnLogin(){
        return webDriver.findElement(By.id("login-button"));
    }

    public void clickOnLoginBtn(){
        WebElement btnLogin = this.getBtnLogin();
        btnLogin.click();
    }

    public void openPage(){
        webDriver.get(url);
        webDriver.manage().window().maximize();
    }


    public WebElement getErrorMessage() {
        return webDriver.findElement(By.xpath("//h3[@data-test='error']"));
    }
}
