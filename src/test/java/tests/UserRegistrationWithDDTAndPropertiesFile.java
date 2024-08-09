package tests;

import data.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

import java.util.Properties;

public class UserRegistrationWithDDTAndPropertiesFile extends TestBase{
    HomePage homePage;
    UserRegistrationPage userRegistrationPage;

    String fn = ReadProperties.userData.getProperty("firstname");
    String ln = ReadProperties.userData.getProperty("lastname");
    String email = ReadProperties.userData.getProperty("email");
    String password = ReadProperties.userData.getProperty("password");
    LoginPage loginPage;
    @Test(priority = 1, alwaysRun = true)
    public void UserCanRegisterSuccefully(){

        homePage = new HomePage(driver);
        homePage.openRegistrationPage();

        userRegistrationPage = new UserRegistrationPage(driver);
        userRegistrationPage.userRegistration(fn,ln,email,password);

        Assert.assertTrue(userRegistrationPage.successMsg.getText().contains("Your registration completed"));
    }

    @Test(dependsOnMethods = {"UserCanRegisterSuccefully"})
    public void RegisterUserCanLogout(){
        userRegistrationPage.userLogout();
    }

    @Test(dependsOnMethods = {"RegisterUserCanLogout"})
    public void RegisterUserCanLogin(){
        loginPage = new LoginPage(driver);
        homePage.openLoginPage();
        loginPage.userLogin(email, password);
        Assert.assertTrue(userRegistrationPage.logoutLink.getText().contains("Log out"));

    }
}
