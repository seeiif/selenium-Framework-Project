package pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends PageBase{
    public HomePage(WebDriver driver){
        super(driver);
        js = (JavascriptExecutor) driver;

    }
    @FindBy(linkText="Contact us")
    WebElement contactUsLink ;
    @FindBy(id="customerCurrency")
    WebElement currencyDropDownList;
    @FindBy(linkText = "Register")
    WebElement registerLink;
    @FindBy(linkText = "Log in")
    WebElement loginLink;

    @FindBy(linkText = "Computers")
    WebElement computerMenu;
    @FindBy(linkText = "Notebooks")
    WebElement noteBooksMenu;

    public void openRegistrationPage(){
        clickButton(registerLink);
    }
    public void openLoginPage(){
        clickButton(loginLink);
    }
    public void openContactUsPage(){
        scrollToButtom();
        clickButton(contactUsLink);
    }
    public void changeCurrency(){
        select = new Select(currencyDropDownList);
        select.selectByVisibleText("Euro");
    }
    public void selectNoteBooksMenu(){
        computerMenu.click();
        // Wait for the notebooks menu to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(noteBooksMenu));
        // Click the notebooks menu
        noteBooksMenu.click();
        //action.moveToElement(computerMenu)
        //      .moveToElement(noteBooksMenu)
         //     .click()
         //     .build()
         //     .perform();
    }


}
