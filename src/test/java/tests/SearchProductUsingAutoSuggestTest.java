package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class SearchProductUsingAutoSuggestTest extends TestBase{
    String productName = "Apple MacBook Pro 13-inch";
    SearchPage searchPage;
    ProductDetailsPage productDetailsPage;

    @Test
    public void UserCanSearchWithAutoSuggest() throws InterruptedException {
        searchPage = new SearchPage(driver);
        searchPage.ProductSearchUsingAUtoSuggest("MacB");
        productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.currentProductName.getText().equalsIgnoreCase(productName));
    }
}
