package tests;

import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.WebDriverUtils;

public class OpenBrowserAndURITest {
    private static WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverUtils.launchChromeDriver();
    }

    @AfterMethod
    public void  tearDown() throws InterruptedException {
        driver.quit();
    }

    @Test
    @Description("Open www.yandex.ru")
    public void loginPositive() {
        driver.get("https://www.yandex.ru");
    }
}
