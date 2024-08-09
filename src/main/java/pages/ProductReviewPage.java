package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;

public class ProductReviewPage extends PageBase{
    public ProductReviewPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="AddProductReview_Title")
    WebElement reviewTitleTxt;
    @FindBy(id="AddProductReview_ReviewText")
    WebElement reviewText;
    @FindBy(id="addproductrating_4")
    WebElement rattingRdoBtn;
    @FindBy(id="add-review")
    WebElement submitReviewBtn;
    @FindBy(css = "span.close")
    WebElement closeMsgNotif;
    @FindBy(css="div.bar-notification.success")
    public WebElement reviewNotification;
    public void AddProductReview(String reviewTitle, String reviewMessage) throws InterruptedException {
        setTextElement(reviewTitleTxt,reviewTitle);
        setTextElement(reviewText, reviewMessage);
        clickButton(rattingRdoBtn);
        clickButton(submitReviewBtn);
        Thread.sleep(1500);
        clickButton(closeMsgNotif);
    }


}
