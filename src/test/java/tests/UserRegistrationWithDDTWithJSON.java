package tests;

import data.JsonReader;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UserRegistrationWithDDTWithJSON extends TestBase{
    HomePage homePage;
    UserRegistrationPage userRegistrationPage;
    LoginPage loginPage;
    @Test(priority = 1, alwaysRun = true)
    public void UserCanRegisterSuccefully() throws IOException, ParseException {

        JsonReader jsReader = new JsonReader();
        jsReader.JsonReader();
        homePage = new HomePage(driver);
        homePage.openRegistrationPage();

        userRegistrationPage = new UserRegistrationPage(driver);
        userRegistrationPage.userRegistration(jsReader.firstname, jsReader.lastname, jsReader.email, jsReader.password);

        Assert.assertTrue(userRegistrationPage.successMsg.getText().contains("Your registration completed"));
        userRegistrationPage.userLogout();

        loginPage = new LoginPage(driver);
        homePage.openLoginPage();
        loginPage.userLogin(jsReader.email, jsReader.password);
        Assert.assertTrue(userRegistrationPage.logoutLink.getText().contains("Log out"));
        userRegistrationPage.userLogout();
    }

}
