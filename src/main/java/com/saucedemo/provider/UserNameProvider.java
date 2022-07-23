package com.saucedemo.provider;

import org.testng.annotations.DataProvider;

public class UserNameProvider {

    @DataProvider(name = "UserNameProvider")
    public static Object[][] extractDataFromDataProvider(){
        return new Object[][] {
                {"standard_user"},
                {"problem_user"}
        };
    }
}
