package tests.user.homework13;

import common.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.user.CartPageObject;
import tests.user.LandingPageObject;
import tests.user.ProductDetailsPageObject;

public class AddToCartTest {
    private WebDriver driver;
    private LandingPageObject landingPageObject;
    private ProductDetailsPageObject productDetailsPage;
    private CartPageObject cartPage;

    @BeforeMethod
    public void setup() {
        driver = WebDriverUtils.launchChromeDriver();
        landingPageObject = new LandingPageObject(driver);
        productDetailsPage = new ProductDetailsPageObject(driver);
        cartPage = new CartPageObject(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void addToCartAndCheck() throws InterruptedException {
        landingPageObject
                .getLandingPageAndWaitForTitle()
                .openOneOfTheLatestProducts(0);
        productDetailsPage
                .writeDownAmountOfItemsInCart()
                .ifSizeSelectionRequiredSelectSmall()
                .pressAddToCartButton()
                .isAmountOfItemsInCartIncreased()
                .openLandingPageAndWait();
        landingPageObject
                .openOneOfTheLatestProducts(1);
        productDetailsPage
                .ifSizeSelectionRequiredSelectSmall()
                .pressAddToCartButton()
                .isAmountOfItemsInCartIncreased()
                .openLandingPageAndWait();
        landingPageObject
                .openOneOfTheLatestProducts(2);
        productDetailsPage
                .ifSizeSelectionRequiredSelectSmall()
                .pressAddToCartButton()
                .isAmountOfItemsInCartIncreased()
                .openCartAndWait();
        cartPage
                .writeAmountOfItemsInSummaryTable()
                .removeFromCart()
                .isAmountOfItemsInSummaryTableDecreased()
                .removeFromCart()
                .isAmountOfItemsInSummaryTableDecreased()
                .removeFromCart()
                .checkThatCartIsEmpty();
    }
}
