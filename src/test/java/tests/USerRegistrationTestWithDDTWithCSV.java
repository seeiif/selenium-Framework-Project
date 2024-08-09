package tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class USerRegistrationTestWithDDTWithCSV extends TestBase{
    HomePage homePage;
    UserRegistrationPage userRegistrationPage;
    LoginPage loginPage;
    CSVReader reader;
    @Test(priority = 1, alwaysRun = true)
    public void UserCanRegisterSuccefully() throws IOException, CsvValidationException {

        String CSV_FILE = System.getProperty("user.dir") + "/src/test/java/data/userData1.csv";
        reader = new CSVReader(new FileReader(CSV_FILE));
        String[] csvCell;
        //While loop will be executed till the last value in CSV file
        while((csvCell = reader.readNext()) != null){
            String firstname = csvCell[0];
            String lastname = csvCell[1];
            String email = csvCell[2];
            String password = csvCell[3];

            homePage = new HomePage(driver);
            homePage.openRegistrationPage();
            userRegistrationPage = new UserRegistrationPage(driver);
            userRegistrationPage = new UserRegistrationPage(driver);
            userRegistrationPage.userRegistration(firstname, lastname,email, password);

            Assert.assertTrue(userRegistrationPage.successMsg.getText().contains("Your registration completed"));
            userRegistrationPage.userLogout();

            loginPage = new LoginPage(driver);
            homePage.openLoginPage();
            loginPage.userLogin(email, password);
            Assert.assertTrue(userRegistrationPage.logoutLink.getText().contains("Log out"));
            userRegistrationPage.userLogout();
        }

    }

}
