package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailFreindPage extends PageBase{
    public EmailFreindPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id="FriendEmail")
    WebElement emailFreindTxt;
    @FindBy(id="PersonalMessage")
    WebElement personalMessageTxt;
    @FindBy(name="send-email")
    WebElement sendEmailBtn;
    @FindBy(css="div.result")
    public WebElement messageNotification;

    public void SendEmailToFreind(String freindEmail, String personalMessage){
        setTextElement(emailFreindTxt, freindEmail);
        setTextElement(personalMessageTxt, personalMessage);
        clickButton(sendEmailBtn);
    }

}
