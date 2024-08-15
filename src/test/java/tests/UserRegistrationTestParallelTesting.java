package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestParallelTesting extends TestBase2{
    HomePage homePage;
    UserRegistrationPage userRegistrationPage;
    LoginPage loginPage;
    Faker fakeDATA = new Faker();
    String firstname = fakeDATA.name().firstName();
    String lastname = fakeDATA.name().lastName();

    String email = fakeDATA.internet().emailAddress();
    String password = fakeDATA.number().digits(8).toString();
    @Test(priority = 1, alwaysRun = true)
    public void UserCanRegisterSuccefully(){

        homePage = new HomePage(getDriver());
        homePage.openRegistrationPage();
        userRegistrationPage = new UserRegistrationPage(getDriver());
        userRegistrationPage.userRegistration(firstname, lastname, email, password);
        System.out.println("The user data is: " + firstname + " " + lastname);
        Assert.assertTrue(userRegistrationPage.successMsg.getText().contains("Your registration completed"));
        userRegistrationPage.userLogout();

        loginPage = new LoginPage(getDriver());
        homePage.openLoginPage();
        loginPage.userLogin(email, password);
        Assert.assertTrue(userRegistrationPage.logoutLink.getText().contains("Log out"));
        userRegistrationPage.userLogout();
    }
}
