package tests.admin.login;

import common.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAsAdminTest {
    private static WebDriver driver;
    private LoginPageObject loginPage;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverUtils.launchChromeDriver();
        loginPage = new LoginPageObject(driver);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        driver.quit();
    }

    @Test
    public void openAdminLoginPage() {
        loginPage
                .getLoginPageAndWaitForLogo()
                .inputUsername("admin")
                .inputPassword("admin")
                .pressLoginButtonAndWaitForLandingPage();
    }
}

