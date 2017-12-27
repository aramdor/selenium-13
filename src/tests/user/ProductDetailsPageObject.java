package tests.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.AssertJUnit.assertTrue;

public class ProductDetailsPageObject {
    protected WebDriver driver;

    public ProductDetailsPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private final String PRODUCT_BOX_XPATH = "//div[@id='box-product']";

    @FindBy(xpath = PRODUCT_BOX_XPATH)
    public WebElement productDetailsBox;

    @FindBy(xpath = PRODUCT_BOX_XPATH + "//*[@itemprop = 'name']")
    private WebElement productName;

    @FindBy(xpath = PRODUCT_BOX_XPATH + "//*[@class = 'price-wrapper']/*[@class = 'regular-price']")
    private WebElement productRegularPrice;

    @FindBy(xpath = PRODUCT_BOX_XPATH + "//*[@class = 'price-wrapper']/*[@class = 'campaign-price']")
    private WebElement productCampaignPrice;



    public String getProductName() {
        return productName.getText();
    }

    public String getProductRegularPrice() {
        return productRegularPrice.getText();
    }

    public String getProductCampaignPrice() {
        return productCampaignPrice.getText();
    }

    public ProductDetailsPageObject isProductRegularPriceGrey() {
        CommonStyleCheckMethods.isThisColorGrey(productRegularPrice.getCssValue("color"));
        return this;
    }

    public ProductDetailsPageObject isProductRegularPriceStrikeThrough() {
        assertTrue("Strikethrough is NOT applied!", productRegularPrice.getCssValue("text-decoration").contains("line-through"));
        return this;
    }


    public ProductDetailsPageObject checkThatBoldIsNotAppliedOnProductRegularPrice() {
        CommonStyleCheckMethods.checkThatBoldIsNotApplied(productRegularPrice.getCssValue("font-weight"));
        return this;
    }

    public ProductDetailsPageObject isProductCampaignPriceColorRed() {
        CommonStyleCheckMethods.isThisColorRed(productCampaignPrice.getCssValue("color"));
        return this;
    }

    public ProductDetailsPageObject isProductCampaignPriceBold() {
        CommonStyleCheckMethods.isThisFontBold(productCampaignPrice.getCssValue("font-weight"));
        return this;
    }

    public void isCampaignPriceFontBiggerThanRegularPrice() {
        CommonStyleCheckMethods.checkThatFontIsBigger(productCampaignPrice.getCssValue("font-size"), productRegularPrice.getCssValue("font-size"));
    }
}
