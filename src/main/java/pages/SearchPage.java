package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends PageBase{
    public SearchPage(WebDriver driver) {
        super(driver);

    }
    @FindBy(id="small-searchterms")
    WebElement searchTxtBox;
    @FindBy(css="button.button-1.search-box-button")
    WebElement searchButton;
    @FindBy(id="ui-id-1")
    List<WebElement> productList;

    @FindBy(css="h2.product-title")
    WebElement productTitle;

    public void productSearch(String productName){
        setTextElement(searchTxtBox, productName);
        clickButton(searchButton);
    }

    public void openProductDetailsPage(){
        clickButton(productTitle);
    }
    public void ProductSearchUsingAUtoSuggest(String searchTxt) throws InterruptedException {
        setTextElement(searchTxtBox, searchTxt);
        Thread.sleep(2000);
        productList.get(0).click();
    }

}
