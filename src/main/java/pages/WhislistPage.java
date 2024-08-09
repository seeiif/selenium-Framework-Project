package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WhislistPage extends PageBase{
    public WhislistPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "td.product")
    public WebElement productCell;

    @FindBy(css = "h1")
    public WebElement wishlistHeader;

    @FindBy(id = "updatecart")
    public WebElement updateWishlistBtn;

    @FindBy(css="button.remove-btn")
    public WebElement removefromCartCheck;

    @FindBy(css = "div.no-data")
    public WebElement EmptyCartLbl;

    public void removeProductFromWishlist() {
        clickButton(removefromCartCheck);

    }
}
