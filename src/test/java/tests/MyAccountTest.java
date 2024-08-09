package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.UserRegistrationPage;

public class MyAccountTest extends TestBase {
    MyAccountPage myAccountPage;
    HomePage homePage;
    LoginPage loginPage;
    UserRegistrationPage userRegistrationPage;
    String oldPassword = "Skchiche@90";
    String newPassword = "Skchiche@24";
    String firstName = "Seif";
    String lastName= "Kchiche";
    String email= "test.test49@gmail.com";

    @Test(priority = 1, alwaysRun = true)
    public void UserCanRegisterSuccefully(){

        homePage = new HomePage(driver);
        homePage.openRegistrationPage();

        userRegistrationPage = new UserRegistrationPage(driver);
        userRegistrationPage.userRegistration(firstName, lastName,email, oldPassword);

        Assert.assertTrue(userRegistrationPage.successMsg.getText().contains("Your registration completed"));
    }

    @Test(dependsOnMethods = {"UserCanRegisterSuccefully"})
    public void RegisterUserCanChangePassword(){
        myAccountPage = new MyAccountPage(driver);
        userRegistrationPage.openMyAccount();
        myAccountPage.openChangePasswordPage();
        myAccountPage.ChangePassword(oldPassword, newPassword);
        Assert.assertTrue(myAccountPage.resultTxt.getText().contains("Password was changed"));

    }
    @Test(dependsOnMethods = {"RegisterUserCanChangePassword"})
    public void RegisterUserCanLogout() throws InterruptedException {
        myAccountPage.setCloseMsgNotif();
        Thread.sleep(3000);
        userRegistrationPage.userLogout();
    }

    @Test(dependsOnMethods = {"RegisterUserCanLogout"})
    public void RegisterUserCanLogin(){
        loginPage = new LoginPage(driver);
        homePage.openLoginPage();
        loginPage.userLogin(email, newPassword);
        Assert.assertTrue(userRegistrationPage.logoutLink.getText().contains("Log out"));

    }
}
