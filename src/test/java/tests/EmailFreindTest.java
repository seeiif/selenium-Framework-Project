package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class EmailFreindTest extends TestBase {
    //1-User registration
    HomePage homePage;
    UserRegistrationPage userRegistrationPage;
    SearchPage searchPage;
    ProductDetailsPage productDetailsPage;
    String productName = "Apple MacBook Pro 13-inch";
    LoginPage loginPage;
    EmailFreindPage emailFreindPage;
    @Test(priority = 1, alwaysRun = true)
    public void UserCanRegisterSuccefully(){

        homePage = new HomePage(driver);
        homePage.openRegistrationPage();

        userRegistrationPage = new UserRegistrationPage(driver);
        userRegistrationPage.userRegistration("Seif", "Kchiche","test.test790@gmail.com", "Skchiche@90");

        Assert.assertTrue(userRegistrationPage.successMsg.getText().contains("Your registration completed"));
    }


    //2- Search for Product
    @Test(priority = 2)
    public void UserCanSearchWithAutoSuggest() throws InterruptedException {
        searchPage = new SearchPage(driver);
        searchPage.ProductSearchUsingAUtoSuggest("MacB");
        productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.currentProductName.getText().equalsIgnoreCase(productName));
    }
    //3-Email to freind
    @Test(priority = 3)
    public void RegisterUserCanSendProductToFreind(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scrollBy(0,750)");
        productDetailsPage.openSendEmail();
        emailFreindPage = new EmailFreindPage(driver);
        emailFreindPage.SendEmailToFreind("seif.seif@te.com", "Hello my freind, check this product");
        Assert.assertTrue(emailFreindPage.messageNotification.getText().contains("Your message has been sent."));
    }
    //4-User can log out
    @Test(priority = 4)
    public void RegisterUserCanLogout(){
        userRegistrationPage = new UserRegistrationPage(driver);
        userRegistrationPage.userLogout();
    }
}
