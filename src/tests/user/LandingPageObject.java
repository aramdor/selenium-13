package tests.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static common.WebDriverUtils.PAGE_LOAD_TIMEOUT_SECONDS;
import static org.testng.AssertJUnit.*;


public class LandingPageObject {
    protected WebDriver driver;

    public LandingPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//header//*[@id='logotype-wrapper']")
    private WebElement headerIcon;

    private static final String xpathContentBlock = "//div[@class='content']";
    private static final String xpathProductInsideBlock = "//li[contains(@class, 'product')]";

    @FindBy(xpath = "//*[@id='box-most-popular']" + xpathProductInsideBlock)
    private List<WebElement> listOfMostPopularProducts;

    @FindBy(xpath = "//*[@id='box-campaigns']" + xpathProductInsideBlock)
    private List<WebElement> listOfProductsInCampaign;

    @FindBy(xpath = "//*[@id='box-latest-products']" + xpathProductInsideBlock)
    private List<WebElement> listOfLatestProducts;

    private String stickerXpath = ".//div[contains(@class, 'sticker')]";

    public LandingPageObject checkThatAllMostPopularProductsContntainsStickers() {
        if (listOfMostPopularProducts.size() == 0) {
            assertTrue("There are NO products in Most popular block", false);
        }
        System.out.println("Most popular products block:");
        for (int productId = 0; productId < listOfMostPopularProducts.size(); productId++) {
            assertTrue("Product " + productId + " has NO sticker!!", listOfMostPopularProducts.get(productId).findElement(By.xpath(stickerXpath)).isDisplayed());
            System.out.println("Product " + productId + " has sticker: " + listOfMostPopularProducts.get(productId).findElement(By.xpath(stickerXpath)).getText());
        }
        return this;
    }

    public LandingPageObject checkThatAllProductsInCampaignContntainsStickers() {
        if (listOfProductsInCampaign.size() == 0) {
            assertTrue("There are NO products in Campaign block", false);
        }
        System.out.println("Campaign products block:");
        for (int productId = 0; productId < listOfProductsInCampaign.size(); productId++) {
            assertTrue("Product " + productId + " has NO sticker!!", listOfProductsInCampaign.get(productId).findElement(By.xpath(stickerXpath)).isDisplayed());
            System.out.println("Product " + productId + " has sticker: " + listOfMostPopularProducts.get(productId).findElement(By.xpath(stickerXpath)).getText());
        }
        return this;
    }

    public LandingPageObject checkThatAllLatestProductsContntainsStickers() {
        if (listOfLatestProducts.size() == 0) {
            assertTrue("There are NO products in Latest products block", false);
        }
        System.out.println("Latest products block:");
        for (int productId = 0; productId < listOfLatestProducts.size(); productId++) {
            assertTrue("Product " + productId + " has NO sticker!!", listOfLatestProducts.get(productId).findElement(By.xpath(stickerXpath)).isDisplayed());
            System.out.println("Product " + productId + " has sticker: " + listOfMostPopularProducts.get(productId).findElement(By.xpath(stickerXpath)).getText());
        }
        return this;
    }

    public LandingPageObject getLandingPageAndWaitForTitle() {
        driver.get("http://localhost/litecart/en/");
        try {
            (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.visibilityOf(headerIcon));
        } catch (WebDriverException ex) {
            assertTrue("Failed to open landing page!", false);
        }
        return this;
    }
}

