package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class ChangeCurrencyTest extends TestBase {

    HomePage homePage;
    String productName = "Apple MacBook Pro 13-inch";
    ProductDetailsPage productDetailsPage;
    SearchPage searchPage;
    @Test
    public void UserCanChangeCurrency(){
        homePage = new HomePage(driver);
        homePage.changeCurrency();
    }
    @Test
    public void UserCanSearchWithAutoSuggest() throws InterruptedException {
        searchPage = new SearchPage(driver);
        searchPage.ProductSearchUsingAUtoSuggest("MacB");
        productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.currentProductName.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(productDetailsPage.productPrice.getText().contains("€"));
        System.out.println(productDetailsPage.productPrice.getText());
    }
}
