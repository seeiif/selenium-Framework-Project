package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TestBase extends AbstractTestNGCucumberTests{
    public static WebDriver driver;
    public static String downloadPath = System.getProperty("user.dir") + "./Downloads";
    public static FirefoxOptions firefoxOption() {
        FirefoxOptions option = new FirefoxOptions();
        option.addPreference("browser.download.folderList", 2);
        option.addPreference("browser.download.dir", downloadPath);
        option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        option.addPreference("browser.download.manager.showWhenStarting", false);
        option.addPreference("pdfjs.disabled", true);
        option.setBinary("C:\\Program Files\\Mozilla Firefox");
        return option;
    }

    public static ChromeOptions chromeOption() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default.content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadPath);
        options.setExperimentalOption("prefs", chromePrefs);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.addArguments("--disable-search-engine-choice-screen");
        return options;
    }
    @BeforeSuite
    @Parameters({"browser"})
    public void startDriver(@Optional ("chrome") String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver126.exe");
            driver = new ChromeDriver(chromeOption());
        }else if(browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            driver= new FirefoxDriver(firefoxOption());
        }else if(browserName.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"/drivers/msedgedriver.exe");
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        driver.navigate().to("http://demo.nopcommerce.com/");
    }
    //takeScreenShot when test cases fails and add it in the screenshotfolder
    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE){
            System.out.println("Failed!");
            System.out.println("Taking ScreenShot...");
            Helper.captureScreenShot(driver, result.getName());
        }
    }
    @AfterSuite
    public void quitDriver(){
        driver.quit();
    }


}
