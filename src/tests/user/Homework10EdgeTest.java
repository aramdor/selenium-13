package tests.user;

import common.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Homework10EdgeTest {
    private WebDriver driver;
    private LandingPageObject landingPage;
    private ProductDetailsPageObject productDetailsPage;
    private int productId = 0;

    @BeforeMethod
    public void setup() {
//        driver = WebDriverUtils.launchChromeDriver();
//        driver = WebDriverUtils.launchFirefoxDriver();
        driver = WebDriverUtils.launchEdgeDriver();
        landingPage = new LandingPageObject(driver);
        productDetailsPage = new ProductDetailsPageObject(driver);
        landingPage
                .getLandingPageAndWaitForTitle();
    }

    @Test(groups = {"homework_10"})
    public void compareProductNameAndPrices() {
        ProductPropertiesObject productOnLandingPage = new ProductPropertiesObject();
        ProductPropertiesObject productOnDetailsPage = new ProductPropertiesObject();
        productOnLandingPage.setProductName(landingPage.getProductName(productId));
        productOnLandingPage.setManufacturer(landingPage.getProductManufacturer(productId));
        productOnLandingPage.setRegularPrice(landingPage.getProductRegularPrice(productId));
        productOnLandingPage.setCampaignPrice(landingPage.getProductCampaignPrice(productId));

        landingPage
                .clickOnProductAndWaitForProductDetailsPage(productId);

        productOnDetailsPage.setProductName(productDetailsPage.getProductName());
        productOnDetailsPage.setRegularPrice(productDetailsPage.getProductRegularPrice());
        productOnDetailsPage.setCampaignPrice(productDetailsPage.getProductCampaignPrice());

        assertEquals("Product names are NOT equal!! ", productOnLandingPage.getProductName(), productOnDetailsPage.getProductName());
        assertEquals("Product regular prices are NOT equal!! ", productOnLandingPage.getRegularPrice(), productOnDetailsPage.getRegularPrice());
        assertEquals("Product campaign prices are NOT equal!! ", productOnLandingPage.getCampaignPrice(), productOnDetailsPage.getCampaignPrice());

        System.out.println("productOnLandingPage " + productOnLandingPage.toString());
        System.out.println("productOnDetailsPage " + productOnDetailsPage.toString());
    }

    @Test(groups = {"homework_10"})
    public void checkProductStyleOnLandingPage() {
        landingPage
                .isProductRegularPriceStrikeThrough(productId)
                .isProductRegularPriceGrey(productId)
                .checkThatBoldIsNotAppliedOnProductRegularPrice(productId)
                .isProductCampaignPriceColorRed(productId)
                .isProductCampaignPriceBold(productId)
                .isCampaignPriceFontBiggerThanRegularPrice(productId);
    }

    @Test(groups = {"homework_10"})
    public void checkProductStyleOnProductDetailsPage() {
        landingPage
                .clickOnProductAndWaitForProductDetailsPage(productId);
        productDetailsPage
                .isProductRegularPriceStrikeThrough()
                .isProductRegularPriceGrey()
                .checkThatBoldIsNotAppliedOnProductRegularPrice()
                .isProductCampaignPriceColorRed()
                .isProductCampaignPriceBold()
                .isCampaignPriceFontBiggerThanRegularPrice();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}


