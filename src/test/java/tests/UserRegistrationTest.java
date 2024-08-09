package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTest extends TestBase {
    HomePage homePage;
    UserRegistrationPage userRegistrationPage;
    LoginPage loginPage;
    @Test(priority = 1, alwaysRun = true)
    public void UserCanRegisterSuccefully(){

        homePage = new HomePage(driver);
        homePage.openRegistrationPage();

        userRegistrationPage = new UserRegistrationPage(driver);
        userRegistrationPage.userRegistration("Seif", "Kchiche","test.test002@gmail.com", "Skchiche@90");

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
        loginPage.userLogin("test.test002@gmail.com", "Skchiche@90");
        Assert.assertTrue(userRegistrationPage.logoutLink.getText().contains("Log out"));

    }
}
