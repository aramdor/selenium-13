package common;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class WebDriverUtils {
    public static int PAGE_LOAD_TIMEOUT_SECONDS = 30;
    private static WebDriver driver;
    private static final String CHROME_WEB_DRIVER_PATH = "Drivers\\chromedriver.exe";
    private static final String FIREFOX_WEB_DRIVER_PATH = "Drivers\\geckodriver.exe";
    private static final String EDGE_WEB_DRIVER_PATH = "Drivers\\MicrosoftWebDriver.exe";

    private static WebDriver getChromeWebDriverPath() {
        File file = new File(CHROME_WEB_DRIVER_PATH);
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        return new ChromeDriver();
    }

    private static WebDriver getFirefoxWebDriverPath() {
        File file = new File(FIREFOX_WEB_DRIVER_PATH);
        System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
        return new FirefoxDriver();
    }

    private static WebDriver getEdgeWebDriverPath() {
        File file = new File(EDGE_WEB_DRIVER_PATH);
        System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
        return new EdgeDriver();
    }

    public static void setDefaultZoom(WebDriver driver) {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.chord(Keys.CONTROL, "0")).perform();
    }

    public static void setupBrowserWaitWindowAndCookies(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        setDefaultZoom(driver);
    }

    public static WebDriver launchChromeDriver() {
        driver = getChromeWebDriverPath();
        setupBrowserWaitWindowAndCookies(driver);
        return driver;
    }

    public static WebDriver launchFirefoxDriver() {
        driver = getFirefoxWebDriverPath();
        setupBrowserWaitWindowAndCookies(driver);
        return driver;
    }

    public static WebDriver launchEdgeDriver() {
        driver = getEdgeWebDriverPath();
        setupBrowserWaitWindowAndCookies(driver);
        return driver;
    }

}
