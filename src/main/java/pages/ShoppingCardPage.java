package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCardPage extends PageBase{
    public ShoppingCardPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(name = "updatecart")
    WebElement removeCheck;

    @FindBy(name = "updatecart")
    WebElement updateCartBtn;

    @FindBy(css = "input.qty-input valid")
    public WebElement quantityTxt;

    @FindBy(css = "td.subtotal")
    public WebElement totalLbl;


    @FindBy(id="checkout")
    WebElement checkoutBtn ;

    @FindBy(id="termsofservice")
    WebElement agreeCheckbox;

    @FindBy(css="button.button-1.checkout-as-guest-button")
    WebElement guestCheckoutBtn ;


    public void RemoveProductFromCart() {
        clickButton(removeCheck);
    }

    public void UpdateProductQuantityInCart(String quantity) {
        //clear quantity textbox
        clearText(quantityTxt);
        setTextElement(quantityTxt, quantity);
        clickButton(updateCartBtn);
    }

    public void openCheckoutPage()
    {
        clickButton(agreeCheckbox);
        clickButton(checkoutBtn);
    }

    public void openCheckoutPageAsGuest()
    {
        clickButton(agreeCheckbox);
        clickButton(checkoutBtn);
        clickButton(guestCheckoutBtn);
    }
}
