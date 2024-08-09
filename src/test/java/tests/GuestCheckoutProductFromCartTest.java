package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.*;
import pages.ShoppingCardPage;

public class GuestCheckoutProductFromCartTest extends TestBase {
    SearchPage searchObject;
    HomePage homeObject;
    ProductDetailsPage productDetails;
    ShoppingCardPage cartObject;
    CheckpoutPage checkoutObject;
    OrderDetailsPage orderObject;
    String productName = "Apple MacBook Pro 13-inch";

    @Test(priority=1)
    public void UserCanSearchForProductsWithAutoSuggest() throws InterruptedException {
        searchObject = new SearchPage(driver);
        searchObject.ProductSearchUsingAUtoSuggest("MacB");
        productDetails = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetails.currentProductName.getText().contains(productName));
    }

    @Test(priority=2)
    public void UserCanAddProductToShoppingCart() throws InterruptedException {
        cartObject = new ShoppingCardPage(driver);
        productDetails.addToCat();
        Thread.sleep(2000);
        driver.navigate().to("http://demo.nopcommerce.com" + "/cart");
        cartObject = new ShoppingCardPage(driver);
        Assert.assertTrue(cartObject.totalLbl.getText().contains("3,600"));
    }

    @Test(priority=3)
    public void UserCanCheckoutProduct() throws InterruptedException {
        checkoutObject = new CheckpoutPage(driver);
        cartObject.openCheckoutPageAsGuest();
        checkoutObject.CheckoutProduct("test", "user", "Egypt"
                , "testuser1250@test.com", "test address", "123456", "32445566677", "Cairo", productName);
        Assert.assertTrue(checkoutObject.prodcutName.isDisplayed());
        Assert.assertTrue(checkoutObject.prodcutName.getText().contains(productName));
        checkoutObject.confirmOrder();
        Assert.assertTrue(checkoutObject.ThankYoulbl.isDisplayed());
    }

    @Test(priority=4)
    public void UserCanViewOrderDetails() throws InterruptedException {
        orderObject = new OrderDetailsPage(driver);
        checkoutObject.viewOrderDetails();
        Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
        orderObject.DownloadPDFInvoice();
        Thread.sleep(3000);
        orderObject.PrintOrderDetails();
    }

}
