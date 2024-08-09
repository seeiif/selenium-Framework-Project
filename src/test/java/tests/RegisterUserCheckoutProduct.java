package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class RegisterUserCheckoutProduct extends TestBase{

/*
1-register User
2-Search for product
3-Add to cart
4-Checkout
5-Logout
 */
    HomePage homeObject ;
    UserRegistrationPage registerObject ;
    LoginPage loginObject ;
    String productName = "Apple MacBook Pro 13-inch";
    SearchPage searchObject ;
    ProductDetailsPage detailsObject ;
    ShoppingCardPage cartPage ;
    CheckpoutPage checkoutObject ;
    OrderDetailsPage orderObject ;

    @Test(priority=1,alwaysRun=true)
    public void UserCanRegisterSuccssfully()
    {
        homeObject = new HomePage(driver);
        homeObject.openRegistrationPage();
        registerObject = new UserRegistrationPage(driver);
        registerObject.userRegistration("Moataz", "Nabil", "test200@gmail.com", "12345678");
        Assert.assertTrue(registerObject.successMsg.getText().contains("Your registration completed"));
    }

    @Test(priority=2)
    public void UserCanSearchWithAutoSuggest()
    {
        try {
            searchObject = new SearchPage(driver);
            searchObject.ProductSearchUsingAUtoSuggest("MacB");
            detailsObject = new ProductDetailsPage(driver);
            Assert.assertEquals(detailsObject.currentProductName.getText(), productName);
        } catch (Exception e) {
            System.out.println("Error occurred  " + e.getMessage());
        }
    }

    @Test(priority=3)
    public void UserCanAddProductToShoppingCart() throws InterruptedException {
        detailsObject = new ProductDetailsPage(driver);
        detailsObject.addToCat();
        Thread.sleep(1000);
        driver.navigate().to("http://demo.nopcommerce.com" + "/cart");
        cartPage = new ShoppingCardPage(driver);
        Assert.assertTrue(cartPage.totalLbl.getText().contains("3,600"));
    }

    @Test(priority=4)
    public void UserCanCheckoutProduct() throws InterruptedException {
        checkoutObject = new CheckpoutPage(driver);
        cartPage.openCheckoutPage();
        checkoutObject.RegisteredUserCheckoutProduct
                ("Egypt", "test address", "123456", "32445566677", "Cairo", productName);
        Assert.assertTrue(checkoutObject.prodcutName.isDisplayed());
        Assert.assertTrue(checkoutObject.prodcutName.getText().contains(productName));

        checkoutObject.confirmOrder();
        Assert.assertTrue(checkoutObject.ThankYoulbl.isDisplayed());
        checkoutObject.viewOrderDetails();
        Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
        orderObject = new OrderDetailsPage(driver);
        orderObject.DownloadPDFInvoice();
        orderObject.PrintOrderDetails();
    }

    @Test(priority=5)
    public void RegisteredUserCanLogout()
    {
        registerObject.userLogout();
    }
}
