package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.*;
import tests.TestBase;

public class EndToEndTest extends TestBase {
    SearchPage searchObject;
    ProductDetailsPage productDetails;
    ShoppingCardPage cartObject;
    CheckpoutPage checkoutObject;
    OrderDetailsPage orderObject;
    String productName = "Apple MacBook Pro 13-inch";

    @Given("^user is on Home Page$")
    public void user_is_on_Home_Page() {
        Assert.assertTrue(driver.getCurrentUrl().contains("demo.nopcommerce.com"));
    }

    @When("^he search for \"([^\"]*)\"$")
    public void he_search_for(String productName) throws InterruptedException {
        searchObject = new SearchPage(driver);
        searchObject.ProductSearchUsingAUtoSuggest(productName);
        productDetails = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetails.currentProductName.getText().contains(productName));
    }

    @When("^choose to buy Two items$")
    public void choose_to_buy_Two_items() throws InterruptedException {
        cartObject = new ShoppingCardPage(driver);
        productDetails.addToCat();
        driver.navigate().to("http://demo.nopcommerce.com/" + "cart");
    }

    @When("^moves to checkout cart and enter personal details on checkout page and place the order$")
    public void moves_to_checkout_cart_and_enter_personal_details_on_checkout_page_and_place_the_order() throws InterruptedException {
        checkoutObject = new CheckpoutPage(driver);
        cartObject.openCheckoutPageAsGuest();
        checkoutObject.CheckoutProduct("test", "user", "Egypt"
                , "testuser1@test.com", "test address", "123456", "32445566677", "Cairo", productName);
        Assert.assertTrue(checkoutObject.prodcutName.isDisplayed());
        Assert.assertTrue(checkoutObject.prodcutName.getText().contains(productName));
        checkoutObject.confirmOrder();
        Assert.assertTrue(checkoutObject.ThankYoulbl.isDisplayed());

    }

    @Then("^he can view the order and download the invoice$")
    public void he_can_view_the_order_and_download_the_invoice() throws InterruptedException {
        orderObject = new OrderDetailsPage(driver);
        checkoutObject.viewOrderDetails();
        Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
        orderObject.DownloadPDFInvoice();
        Thread.sleep(3000);
        orderObject.PrintOrderDetails();
    }
}
