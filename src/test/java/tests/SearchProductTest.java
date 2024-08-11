package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class SearchProductTest extends TestBase{
    String productName = "Apple MacBook Pro 13-inch";
    SearchPage searchPage;
    ProductDetailsPage productDetailsPage;

    @Test
    public void userCanSearchForProducts() throws InterruptedException {
        searchPage = new SearchPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        searchPage.productSearch(productName);
        searchPage.openProductDetailsPage();
        Thread.sleep(3000);
        Assert.assertTrue(productDetailsPage.currentProductName.getText().equalsIgnoreCase(productName));
        //Assert.assertEquals(productDetailsPage.currentProductName.getText(), productName);
    }
}
