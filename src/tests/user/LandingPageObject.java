package tests.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.user.registration.EmailObject;

import java.util.List;

import static common.WebDriverUtils.PAGE_LOAD_TIMEOUT_SECONDS;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


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
    public List<WebElement> listOfLatestProducts;

    private String stickerXpath = ".//div[contains(@class, 'sticker')]";

    @FindBy(xpath = "//div[@id='box-campaigns']")
    private WebElement campaigns;

    private String productNameXpath = ".//*[@class='name']";
    private String productManufacturerXpath = ".//*[@class='manufacturer']";
    private String productRegularPriceXpath = ".//*[@class='regular-price']";
    private String productCampaignPriceXpath = ".//*[@class='campaign-price']";


    public LandingPageObject checkThatAllMostPopularProductsContntainsStickers() {
        if (listOfMostPopularProducts.size() == 0) {
            assertTrue("There are NO products in Most popular block", false);
        }
        System.out.println("Most popular products block:");
        for (int productId = 0; productId < listOfMostPopularProducts.size(); productId++) {
            System.out.println("Product " + productId + " has sticker: " + listOfMostPopularProducts.get(productId).findElement(By.xpath(stickerXpath)).getText());
            assertEquals("Product " + productId + " has NO sticker or too many stickers!!", 1, listOfMostPopularProducts.get(productId).findElements(By.xpath(stickerXpath)).size());
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
            assertEquals("Product " + productId + " has NO sticker or too many stickers!!", 1, listOfProductsInCampaign.get(productId).findElements(By.xpath(stickerXpath)).size());
            System.out.println("Product " + productId + " has sticker: " + listOfMostPopularProducts.get(productId).findElement(By.xpath(stickerXpath)).getText());

        }
        return this;
    }

    public void checkThatAllLatestProductsContntainsStickers() {
        if (listOfLatestProducts.size() == 0) {
            assertTrue("There are NO products in Latest products block", false);
        }
        System.out.println("Latest products block:");
        for (int productId = 0; productId < listOfLatestProducts.size(); productId++) {
            assertEquals("Product " + productId + " has NO sticker or too many stickers!!", 1, listOfLatestProducts.get(productId).findElements(By.xpath(stickerXpath)).size());
            System.out.println("Product " + productId + " has sticker: " + listOfMostPopularProducts.get(productId).findElement(By.xpath(stickerXpath)).getText());
        }
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


    ////////////////////////////////////////////////////////Homework 10 methods///////////////////////////////////////////////////

    private WebElement getProductData(int productId, String xpath) {
        if (listOfProductsInCampaign.size() == 0) {
            assertTrue("There are NO products in Campaign block", false);
        }
        return listOfProductsInCampaign.get(productId).findElement(By.xpath(xpath));
    }

    public String getProductName(int productId) {
        return getProductData(productId, productNameXpath).getText();
    }

    public String getProductManufacturer(int productId) {
        return getProductData(productId, productManufacturerXpath).getText();
    }

    public String getProductRegularPrice(int productId) {
        return getProductData(productId, productRegularPriceXpath).getText();
    }

    public String getProductCampaignPrice(int productId) {
        return getProductData(productId, productCampaignPriceXpath).getText();
    }

    public void clickOnProductAndWaitForProductDetailsPage(int productId) {
        listOfProductsInCampaign.get(productId).click();
        ProductDetailsPageObject productDetailsPage = new ProductDetailsPageObject(driver);
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(productDetailsPage.productDetailsBox));
    }


    private String getCSSvalue(int productId, String xpath, String css) {
        return getProductData(productId, xpath).getCssValue(css);
    }

    public LandingPageObject isProductRegularPriceGrey(int productId) {
        CommonStyleCheckMethods.isThisColorGrey(getCSSvalue(productId, productRegularPriceXpath, "color"));
        return this;
    }

    public LandingPageObject isProductRegularPriceStrikeThrough(int productId) {
        assertTrue("Strikethrough is NOT applied!", getCSSvalue(productId, productRegularPriceXpath, "text-decoration").contains("line-through"));
        return this;
    }


    public LandingPageObject checkThatBoldIsNotAppliedOnProductRegularPrice(int productId) {
        CommonStyleCheckMethods.checkThatBoldIsNotApplied(getProductData(productId, productRegularPriceXpath).getCssValue("font-weight"));
        return this;
    }

    public LandingPageObject isProductCampaignPriceColorRed(int productId) {
        CommonStyleCheckMethods.isThisColorRed(getProductData(productId, productCampaignPriceXpath).getCssValue("color"));
        return this;
    }

    public LandingPageObject isProductCampaignPriceBold(int productId) {
        CommonStyleCheckMethods.isThisFontBold(getProductData(productId, productCampaignPriceXpath).getCssValue("font-weight"));
        return this;
    }

    public void isCampaignPriceFontBiggerThanRegularPrice(int productId) {
        CommonStyleCheckMethods.checkThatFontIsBigger(getProductData(productId, productCampaignPriceXpath).getCssValue("font-size"), getProductData(productId, productRegularPriceXpath).getCssValue("font-size"));
    }

    ////////////////////////////////////////////////Homework 11//////////////////////////////////////////////


    @FindBy(xpath = "//*[@href='http://localhost/litecart/en/logout']")
    public WebElement logoutButton;

    @FindBy(xpath = "//button[@name='login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInputField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInputField;

    public LandingPageObject isItLandingPage() {
        driver.get("http://localhost/litecart/en/");
        try {
            headerIcon.isDisplayed();
        } catch (WebDriverException ex) {
            assertTrue("It is NOT landing page!", false);
        }
        return this;
    }

    public LandingPageObject logoutAndWait() {
        logoutButton.click();
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(loginButton));
        return this;
    }

    public LandingPageObject inputEmailAndPasswordToLoginForm(EmailObject emailObject) {
        emailInputField.sendKeys(emailObject.getEmailWithIncrement());
        passwordInputField.sendKeys(emailObject.getPassword());
        return this;
    }

    public LandingPageObject pressLoginButtonAndWait() {
        loginButton.click();
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(logoutButton));
        return this;
    }


    //////////////////////////////////Homework 13//////////////////////////////////
    public LandingPageObject openOneOfTheLatestProducts(int productId) {
        if (listOfLatestProducts.size() < productId) {
            assertTrue("THere is NO such product", false);
        }
        listOfLatestProducts.get(productId).click();
        ProductDetailsPageObject productDetailsPage = new ProductDetailsPageObject(driver);
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(productDetailsPage.productDetailsBox));
        return this;
    }


}

