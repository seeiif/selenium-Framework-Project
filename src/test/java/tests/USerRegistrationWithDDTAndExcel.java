package tests;

import data.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

import java.io.IOException;

public class USerRegistrationWithDDTAndExcel extends TestBase{
    HomePage homePage;
    UserRegistrationPage userRegistrationPage;

    LoginPage loginPage;

    @DataProvider(name="ExcelData")
    public Object[][] userRegisterData() throws IOException {
        //get data from Excel Reader class
        ExcelReader er = new ExcelReader();
            return er.getExcelData();
    }
    @Test(priority = 1, alwaysRun = true, dataProvider = "ExcelData")
    public void UserCanRegisterSuccefully(String firstName, String lastName, String email, String password){

        homePage = new HomePage(driver);
        homePage.openRegistrationPage();

        userRegistrationPage = new UserRegistrationPage(driver);
        userRegistrationPage.userRegistration(firstName, lastName, email, password);

        Assert.assertTrue(userRegistrationPage.successMsg.getText().contains("Your registration completed"));
        userRegistrationPage.userLogout();

        loginPage = new LoginPage(driver);
        homePage.openLoginPage();
        loginPage.userLogin(email, password);
        Assert.assertTrue(userRegistrationPage.logoutLink.getText().contains("Log out"));
        userRegistrationPage.userLogout();
    }
}
