package tests.admin.landing;

import common.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.admin.leftsidebar.LeftsidebarForLandingPageObject;
import tests.admin.login.LoginPageObject;

import static org.testng.AssertJUnit.assertTrue;

public class homework9 {
    private WebDriver driver;
    private LoginPageObject loginPage;
    private LeftsidebarForLandingPageObject leftsidebarForLandingPage;
    private LandingPageForAdminPageObject landingForAdminPage;

    @BeforeMethod
    public void setup() {
        driver = WebDriverUtils.launchChromeDriver();
        loginPage = new LoginPageObject(driver);
        leftsidebarForLandingPage = new LeftsidebarForLandingPageObject(driver);
        landingForAdminPage = new LandingPageForAdminPageObject(driver);
    }

    @Test
    public void checkCountiesSorting() {
        loginPage
                .loginUnderDefaultAdminAccountAndWaitForLandingPage();
        leftsidebarForLandingPage
                .selectCountriesAndWait();
        landingForAdminPage
                .checkCountryNamesSorting();

    }

    @Test
    public void checkZonesSortingInsideCounties() {
        loginPage
                .loginUnderDefaultAdminAccountAndWaitForLandingPage();
        leftsidebarForLandingPage
                .selectCountriesAndWait();
        int amountOfCountriesWithZones = landingForAdminPage.getCountriesWithZones().size();
        if (amountOfCountriesWithZones == 0) {
            assertTrue("List of countries with Zones is empty", false);
        }
        for (int i = 0; i < amountOfCountriesWithZones; i++) {
            landingForAdminPage.openCountryForEditAndWait(landingForAdminPage.getCountriesWithZones().get(i));
            landingForAdminPage.checkZonesSorting();
            leftsidebarForLandingPage
                    .selectCountriesAndWait();
        }
    }

    @Test
    public void checkZonesSortingInsideGeoZones() {
        loginPage
                .loginUnderDefaultAdminAccountAndWaitForLandingPage();
        leftsidebarForLandingPage
                .selectGeoZonesAndWait();
        int amountOfCountriesAtPage = landingForAdminPage.getListOfCountiresForGZ().size();
        if (amountOfCountriesAtPage == 0) {
            assertTrue("List of countries at GeoZone page is empty", false);
        }
        for (int i = 0; i < amountOfCountriesAtPage; i++) {
            landingForAdminPage.openGZCountryForEditAndWait(landingForAdminPage.getListOfCountiresForGZ().get(i));
            landingForAdminPage.checkZonesSortingForGZ();
            leftsidebarForLandingPage
                    .selectGeoZonesAndWait();
        }
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
