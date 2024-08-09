package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.WhislistPage;

public class AddProductToWishListTest extends TestBase{
    String productName = "Apple MacBook Pro 13-inch";
    SearchPage searchPage;
    ProductDetailsPage productDetailsPage;
    WhislistPage wishlistObject;

    @Test
    public void UserCanSearchWithAutoSuggest() throws InterruptedException {
        searchPage = new SearchPage(driver);
        searchPage.ProductSearchUsingAUtoSuggest("MacB");
        productDetailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPage.currentProductName.getText().equalsIgnoreCase(productName));
    }
    @Test(priority=2)
    public void UserCanAddProductToWishlist() throws InterruptedException {
        productDetailsPage = new ProductDetailsPage(driver);
        productDetailsPage.addProductToWishlist();
        driver.navigate().to("http://demo.nopcommerce.com" + "/wishlist");
        wishlistObject = new WhislistPage(driver);
        Assert.assertTrue(wishlistObject.wishlistHeader.isDisplayed());
        Assert.assertTrue(wishlistObject.productCell.getText().contains(productName));
    }

    @Test(priority=3)
    public void UserCanRemoveProductFromCart() {
        wishlistObject = new WhislistPage(driver);
        wishlistObject.removeProductFromWishlist();
        Assert.assertTrue(wishlistObject.EmptyCartLbl.getText().contains("The wishlist is empty!"));
    }
}
