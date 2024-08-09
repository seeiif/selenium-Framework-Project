package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationWithDataProviderTest extends TestBase{
    HomePage homePage;
    UserRegistrationPage userRegistrationPage;

    LoginPage loginPage;

    @DataProvider(name="testData")
    public static Object[][] userData(){
        return new Object[][]{{"Seif", "Kchich","test84@gmail.com", "12548525"},
                {"Seif", "Kchich","test49@gmail.com", "12548525"}};
    }
    @Test(priority = 1, alwaysRun = true, dataProvider = "testData")
    public void UserCanRegisterSuccefully(String fname, String lastname, String email, String password){

        homePage = new HomePage(driver);
        homePage.openRegistrationPage();
        userRegistrationPage = new UserRegistrationPage(driver);
        userRegistrationPage = new UserRegistrationPage(driver);
        userRegistrationPage.userRegistration(fname, lastname,email, password);

        Assert.assertTrue(userRegistrationPage.successMsg.getText().contains("Your registration completed"));
        userRegistrationPage.userLogout();

        loginPage = new LoginPage(driver);
        homePage.openLoginPage();
        loginPage.userLogin(email, password);
        Assert.assertTrue(userRegistrationPage.logoutLink.getText().contains("Log out"));
        userRegistrationPage.userLogout();
    }

}
