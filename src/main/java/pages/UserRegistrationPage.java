package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRegistrationPage extends PageBase{
    public UserRegistrationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="gender-male")
    WebElement genderRdoBtn;

    @FindBy(id="FirstName")
    WebElement firstNameTxt;

    @FindBy(id="LastName")
    WebElement lastNameTxt;

    @FindBy(id="Email")
    WebElement emailTxt;

    @FindBy(id="Password")
    WebElement passwordTxt;

    @FindBy(id="ConfirmPassword")
    WebElement confirmPasswordTxt;

    @FindBy(id="register-button")
    WebElement registerButton;

    @FindBy(css = "div.result")
    public WebElement successMsg;

    @FindBy(css = "a.ico-logout")
    public WebElement logoutLink;

    @FindBy(linkText = "My account")
    WebElement myaccountLink;

    public void userRegistration(String firstName,String lastName, String email, String password){
        clickButton(genderRdoBtn);
        setTextElement(firstNameTxt, firstName);
        setTextElement(lastNameTxt, lastName);
        setTextElement(emailTxt, email);
        setTextElement(passwordTxt, password);
        setTextElement(confirmPasswordTxt, password);
        clickButton(registerButton);
    }

    public void userLogout(){
        clickButton(logoutLink);
    }

    public void openMyAccount(){
        clickButton(myaccountLink);
    }



}
