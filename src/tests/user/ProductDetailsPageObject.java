package tests.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static common.WebDriverUtils.PAGE_LOAD_TIMEOUT_SECONDS;
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

    //////////////////////////////Homework 13//////////////////////////////
    @FindBy(xpath="//form[@name='buy_now_form']")
    private WebElement byNowForm;

    @FindBy(xpath="//select[@name='options[Size]']")
    private WebElement sizeOptionsDropdown;

    @FindBy(xpath="//option[@value='Small']")
    private WebElement smallSizeFromDropDown;


    public ProductDetailsPageObject ifSizeSelectionRequiredSelectSmall() {
        if (byNowForm.getAttribute("outerHTML").contains("options[Size]")) {
            sizeOptionsDropdown.click();
            smallSizeFromDropDown.click();
        }
        return this;
    }

    @FindBy(xpath="//button[@name='add_cart_product']")
    private WebElement addToCartButton;

    public ProductDetailsPageObject pressAddToCartButton() {
        addToCartButton.click();
        return this;
    }

    @FindBy(xpath="//div[@id='logotype-wrapper']")
    private WebElement litecartLogo;

    public void openLandingPageAndWait() {
        LandingPageObject landingPage = new LandingPageObject(driver);
        litecartLogo.click();
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(landingPage.listOfLatestProducts.get(0)));
    }

    private int amountOfItemsInCart;

    @FindBy(xpath="//div[@id='cart']//span[@class='quantity']")
    private WebElement cartQuantity;

    public ProductDetailsPageObject writeDownAmountOfItemsInCart() {
        this.amountOfItemsInCart = Integer.parseInt(cartQuantity.getText());
        return this;
    }

    public ProductDetailsPageObject isAmountOfItemsInCartIncreased() throws InterruptedException {
        for (int counter = 0; counter <= 5; counter++) {
            if (Integer.parseInt(cartQuantity.getText()) > this.amountOfItemsInCart)
            {
                break;
            }
            Thread.sleep(500);
        }
        if (Integer.parseInt(cartQuantity.getText()) <= this.amountOfItemsInCart)
        {
            assertTrue("Amount of items in cart was NOT increased", false);
        }
        this.amountOfItemsInCart = Integer.parseInt(cartQuantity.getText());
        return this;
    }

    public void openCartAndWait() {
        CartPageObject cartPage = new CartPageObject(driver);
        cartQuantity.click();
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(cartPage.orderSummaryTable));
    }
}
