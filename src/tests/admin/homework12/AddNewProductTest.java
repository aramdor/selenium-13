package tests.admin.homework12;

import common.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.admin.leftsidebar.LeftsidebarForLandingPageObject;
import tests.admin.login.LoginPageObject;

public class AddNewProductTest {
    private WebDriver driver;
    private LoginPageObject loginPage;
    private LeftsidebarForLandingPageObject leftsidebarForLandingPage;
    private CatalogPageObject catalogPageObject;

    @BeforeMethod
    public void setup() {
        driver = WebDriverUtils.launchChromeDriver();
        loginPage = new LoginPageObject(driver);
        leftsidebarForLandingPage = new LeftsidebarForLandingPageObject(driver);
        catalogPageObject = new CatalogPageObject(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void addNewProduct() {
        loginPage
                .getLoginPageAndWaitForLogo()
                .loginUnderDefaultAdminAccountAndWaitForLandingPage();
        leftsidebarForLandingPage
                .openCatalogAndWait();
        catalogPageObject
                .saveAmountOfProductsAtBegining()
                .clickOnAddNewProductButton()
                .openGeneralTabAndWait()
                .setStatusToEnabled()
                .setProductName("Speedo Goggles")
                .setProductCode("sp001")
                .setQuantity("1")
                .setImage();
        catalogPageObject
                .openInformationTabAndWait()
                .selectManufacturer()
                .setShortDescription("Speedo V-Class Mirror Goggles")
                .setDescription("The sci-fi-looking mirrored lenses on these make the pair a stylish option to hit the pool in. And Speedo claims the anti-fog coating on the lens will last twice as long as regular goggles. We could not test this but we definitely did not see any fogging during our one-hour swim. The seal around the eyes is water tight, while the band around the head is easily adjustable and comfortable –there is no chance of your hair getting tangled in the straps. The goggles feel as good as they look, leaving no marks on the face, even after a long swim.");
        catalogPageObject
                .openPricesTabAndWait()
                .setPurchasePrice("45")
                .setUSDPrice("45")
                .saveNewProduct()
                .checkThatAmountOfProductsWereIncreased();
    }
}
