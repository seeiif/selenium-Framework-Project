package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends PageBase{
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="div.product-name")
    public WebElement currentProductName;
    @FindBy(css="button.button-2.email-a-friend-button")
    public WebElement emailFreindBTN;
    @FindBy(css="span.price-value-4")
    public WebElement productPrice;
    @FindBy(linkText="Add your review")
    public WebElement addReviewLink;
    @FindBy(id="add-to-wishlist-button-4")
    public WebElement addWishListBtn;
    @FindBy(css="button.button-2.add-to-compare-list-button")
    WebElement addToCompareBtn;
    @FindBy(id="add-to-cart-button-4")
    WebElement addToCardBtn;
    public void openSendEmail(){
        clickButton(emailFreindBTN);
    }
    public void addProductReview(){
        clickButton(addReviewLink);
    }
    public void addProductToWishlist(){
        clickButton(addWishListBtn);
    }
    public void addProductToCompare(){
        clickButton(addToCompareBtn);
    }
    public void addToCat(){
        clickButton(addToCardBtn);
    }
}
