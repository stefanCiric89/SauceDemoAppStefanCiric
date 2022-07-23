package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;


public class BasePage {

    protected WebDriver webDriver;
    protected String url;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.url = "https://www.saucedemo.com/";
    }

    public BasePage() {}
}
