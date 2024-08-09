package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;

public class ContactUsTest extends TestBase{
    HomePage homePage;
    ContactUsPage contactUsPage;
    String email = "test.test@gmaill.com";
    String fullName = "Test user";
    String enquiry = "Hello admis, this is for test";

    @Test
    public void UserCanContactUs(){
        homePage = new HomePage(driver);
        homePage.openContactUsPage();
        contactUsPage =new ContactUsPage(driver);
        contactUsPage.UserCanContactUs(fullName, email, enquiry);
        Assert.assertTrue(contactUsPage.successMessage.getText().contains("Your enquiry has been successfully sent to the store owner."));
    }
}
