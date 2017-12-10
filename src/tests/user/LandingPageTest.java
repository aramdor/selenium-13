package tests.user;

import common.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tests.admin.leftsidebar.LeftsidebarForLandingPageObject;
import tests.admin.login.LoginPageObject;

public class LandingPageTest {
    private WebDriver driver;
    private LandingPageObject landingPage;

    @BeforeTest
    public void setup() {
        driver = WebDriverUtils.launchChromeDriver();
        landingPage = new LandingPageObject(driver);
    }

    @Test
    public void goThrougtTheListOfLeftSidebarItems() {
        landingPage
                .getLandingPageAndWaitForTitle()
                .checkThatAllMostPopularProductsContntainsStickers()
                .checkThatAllProductsInCampaignContntainsStickers()
                .checkThatAllLatestProductsContntainsStickers();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
