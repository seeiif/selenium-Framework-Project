package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends PageBase{
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Change password")
    WebElement changePasswordLink;

    @FindBy(id="OldPassword")
    WebElement oldPasswordTxt;

    @FindBy(id="NewPassword")
    WebElement newPasswordTxt;

    @FindBy(id="ConfirmNewPassword")
    WebElement confirmPassword;

    @FindBy(css = "button.button-1.change-password-button")
    WebElement changePasswordBtn;
    @FindBy(xpath = "//*[@id=\"bar-notification\"]/div/p")
    public  WebElement resultTxt;

    @FindBy(xpath = "//*[@id=\"bar-notification\"]/div/span")
    public WebElement closeMsgNotif;
    public void openChangePasswordPage(){
         clickButton(changePasswordLink);
    }

    public void setCloseMsgNotif(){
        clickButton(closeMsgNotif);
    }

    public void ChangePassword(String oldPassword, String newPassword){
        setTextElement(oldPasswordTxt, oldPassword);
        setTextElement(newPasswordTxt, newPassword);
        setTextElement(confirmPassword, newPassword);
        clickButton(changePasswordBtn);
    }

}
