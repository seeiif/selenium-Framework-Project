package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class AddProductReviewTest extends TestBase{
/*
*1-user registration
* 2- Search for product
* 3-Add review
* 4-Log out
 */
HomePage homePage;
    UserRegistrationPage userRegistrationPage;
    SearchPage searchPage;
    ProductDetailsPage productDetailsPage;
    String productName = "Apple MacBook Pro 13-inch";
    LoginPage loginPage;
    EmailFreindPage emailFreindPage;
    ProductReviewPage productReviewPage;
    @Test(priority = 1, alwaysRun = true)
    public void UserCanRegisterSuccefully(){

        homePage = new HomePage(driver);
        homePage.openRegistrationPage();

        userRegistrationPage = new UserRegistrationPage(driver);
        userRegistrationPage.userRegistration("Seif", "Kchiche","test.test0510@gmail.com", "Skchiche@90");
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
    //3-AddReview
    @Test(priority = 3)
    public void RegisterUserCanReviewProduct() throws InterruptedException {
        productDetailsPage.addProductReview();
        productReviewPage = new ProductReviewPage(driver);
        productReviewPage.AddProductReview("new review", "the product is very good");
        Assert.assertTrue(productReviewPage.reviewNotification.getText().contains("Product review is successfully added."));
    }

    //4-User can log out
    @Test(priority = 4)
    public void RegisterUserCanLogout(){
        userRegistrationPage = new UserRegistrationPage(driver);
        userRegistrationPage.userLogout();
    }
}
