package com.saucedemo.provider;

import org.testng.annotations.DataProvider;

public class InvalidUserNameProvider {

    @DataProvider(name = "InvalidUserNameProvider")
    public static Object[][] extractDataFromDataProvider() {
        return new Object[][] {
                {"raoul_duke"},
                {"alex_deLarge"}
        };
    }
}
