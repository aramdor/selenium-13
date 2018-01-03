package tests.user;

import common.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class LandingPageTest {
    private WebDriver driver;
    private LandingPageObject landingPage;

    @BeforeMethod
    public void setup() {
        driver = WebDriverUtils.launchChromeDriver();
        landingPage = new LandingPageObject(driver);
    }

//    @Test
    public void goThrougtTheListOfLeftSidebarItems() {
        landingPage
                .getLandingPageAndWaitForTitle()
                .checkThatAllMostPopularProductsContntainsStickers()
                .checkThatAllProductsInCampaignContntainsStickers()
                .checkThatAllLatestProductsContntainsStickers();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
