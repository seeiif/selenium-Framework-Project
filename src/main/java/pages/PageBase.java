package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageBase {

    public WebDriver driver;
    public JavascriptExecutor js;
    public Select select;

    //create a constructor
    public PageBase(WebDriver driver){

        PageFactory.initElements(driver, this);
    }

    public static void clickButton(WebElement button){
          button.click();
    }
    public static void setTextElement(WebElement textElement, String value){
        textElement.sendKeys(value);
    }

    public void scrollToButtom(){
       js.executeScript("scrollBy(0,2500)");
    }
    public void clearText(WebElement element){
        element.clear();
    }


}
