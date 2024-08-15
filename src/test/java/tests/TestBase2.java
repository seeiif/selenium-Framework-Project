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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Helper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TestBase2 {
    public static String baseUrl = "http://demo.nopcommerce.com";

    protected ThreadLocal<RemoteWebDriver> driver = null;
    @BeforeClass
    @Parameters(value={"browser"})
    public void SetUp(@Optional("chrome") String browser) throws MalformedURLException {
        driver = new ThreadLocal<>();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", browser);
        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps));
        getDriver().navigate().to(baseUrl);
    }

    public WebDriver getDriver(){
        return driver.get();
    }
    @AfterClass
    public void stopDriver(){
        getDriver().quit();
        driver.remove();
    }
    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE){
            System.out.println("Failed!");
            System.out.println("Taking ScreenShot...");
            Helper.captureScreenShot(getDriver(), result.getName());
        }
    }
}
