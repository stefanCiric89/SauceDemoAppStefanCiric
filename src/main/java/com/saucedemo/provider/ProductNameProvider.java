package com.saucedemo.provider;

import org.testng.annotations.DataProvider;

public class ProductNameProvider {

    @DataProvider(name = "ProductNameProvider")
    public static Object[][] extractDataFromDataProvider(){
        return new Object[][] {
                //{"Sauce Labs Backpack"},
                {"Sauce Labs Bike Light"},
                {"Sauce Labs Bolt T-Shirt"},
                {"Sauce Labs Fleece Jacket"},
                {"Sauce Labs Onesie"},
                {"Test.allTheThings() T-Shirt (Red)"}
        };
    }
}