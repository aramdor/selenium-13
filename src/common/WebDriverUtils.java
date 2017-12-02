package common;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class WebDriverUtils {
    public static int PAGE_LOAD_TIMEOUT_SECONDS = 30;
    private static WebDriver driver;
    private static final String CHROME_WEB_DRIVER_PATH = "Drivers\\chromedriver.exe";

    private static WebDriver getChromeWebDriverPath() {
        File file = new File(CHROME_WEB_DRIVER_PATH);
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        return new ChromeDriver();
    }

    public static void setDefaultZoom(WebDriver driver) {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.chord(Keys.CONTROL, "0")).perform();
    }

    public static void setupBrowserWaitWindowAndCookies(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        setDefaultZoom(driver);
    }

    public static WebDriver launchChromeDriver() {
        driver = getChromeWebDriverPath();
        setupBrowserWaitWindowAndCookies(driver);
        return driver;
    }

}
