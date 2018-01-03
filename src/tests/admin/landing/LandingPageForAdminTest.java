package tests.admin.landing;

import common.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.admin.leftsidebar.LeftsidebarForLandingPageObject;
import tests.admin.login.LoginPageObject;

public class LandingPageForAdminTest {
    private WebDriver driver;
    private LoginPageObject loginPage;
    private LeftsidebarForLandingPageObject leftsidebarForLandingPage;

    @BeforeMethod
    public void setup() {
        driver = WebDriverUtils.launchChromeDriver();
        loginPage = new LoginPageObject(driver);
        leftsidebarForLandingPage = new LeftsidebarForLandingPageObject(driver);
    }

    @Test
    public void goThrougtTheListOfLeftSidebarItems() {
        loginPage
                .loginUnderDefaultAdminAccountAndWaitForLandingPage();
        leftsidebarForLandingPage
                .clickThroughAllSidebarElements();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
