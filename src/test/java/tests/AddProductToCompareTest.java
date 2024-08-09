package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class AddProductToCompareTest extends TestBase{

    //1-Search For Product number1
    //2-Search For Product number2
    //3-Add to compare
    //4-Clear list

    ProductDetailsPage productDetailsPage;
    UserRegistrationPage registerObject;
    HomePage homePage;
    ComparePage comparePage;
    SearchPage searchPage;
    @Test(priority=1)
    public void userCanCompareProduct() throws InterruptedException {
        searchPage = new SearchPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        comparePage = new ComparePage(driver);

        searchPage.ProductSearchUsingAUtoSuggest("MacB");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scrollBy(0,700)");
        Assert.assertTrue(productDetailsPage.currentProductName.getText().contains("Apple MacBook Pro 13-inch"));
        productDetailsPage.addProductToCompare();

        searchPage.ProductSearchUsingAUtoSuggest("Asus");
        js.executeScript("scrollBy(0,600)");
        Thread.sleep(1000);
        Assert.assertTrue(productDetailsPage.currentProductName.getText().contains("Asus N551JK-XO076H Laptop"));
        productDetailsPage.addProductToCompare();
        Thread.sleep(1000);

        driver.navigate().to("http://demo.nopcommerce.com" + "/compareproducts");
        js.executeScript("scrollBy(0,200)");
        Assert.assertTrue(comparePage.firstProductName.getText().equals("Asus N551JK-XO076H Laptop"));
        Assert.assertTrue(comparePage.secondProductName.getText().equals("Apple MacBook Pro 13-inch"));
        comparePage.compareproducts();
    }

    @Test(priority=2)
    public void userCanClearComareList(){
        comparePage.clearCompareList();
        Assert.assertTrue(comparePage.noDataLb1.getText().contains("You have no items to compare."));
    }
    @Test(priority=5)
    public void RegisteredUserCanLogout()
    {
        registerObject = new UserRegistrationPage(driver);
        registerObject.userLogout();
    }


}
