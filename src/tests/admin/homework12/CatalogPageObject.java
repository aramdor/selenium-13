package tests.admin.homework12;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

import static common.WebDriverUtils.PAGE_LOAD_TIMEOUT_SECONDS;
import static org.testng.AssertJUnit.assertTrue;

public class CatalogPageObject {
    protected WebDriver driver;
    private int amountOfProductsBeforeTest;

    public CatalogPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@href='http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product']")
    private WebElement addNewProductButton;

    public CatalogPageObject clickOnAddNewProductButton() {
        addNewProductButton.click();
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(generalTab));
        return this;
    }

    @FindBy(xpath="//a[@href='#tab-general']")
    private WebElement generalTab;

    public CatalogPageObject openGeneralTabAndWait() {
        generalTab.click();
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(enabledRadioButton));
        return this;
    }

    @FindBy(xpath = "//input[@name='status']")
    private WebElement enabledRadioButton;

    public CatalogPageObject setStatusToEnabled() {
        enabledRadioButton.click();
        return this;
    }

    @FindBy(xpath = "//input[@name='name[en]']")
    private WebElement productName;


    public CatalogPageObject setProductName(String name) {
        productName.sendKeys(name);
        return this;
    }

    @FindBy(xpath = "//input[@name='code']")
    private WebElement productCode;

    public CatalogPageObject setProductCode(String code) {
        productCode.sendKeys(code);
        return this;
    }

    @FindBy(xpath = "//input[@name='quantity']")
    private WebElement productQuantity;

    public CatalogPageObject setQuantity(String quantity) {
        productQuantity.sendKeys(Keys.DELETE + quantity);
        return this;
    }

    @FindBy(xpath = "//input[@name='new_images[]']")
    private WebElement productImage;


    public CatalogPageObject setImage() {
        String filepath = "src/tests/admin/homework12/swimmingGoggles.jpg";
        File file = new File(filepath);
        productImage.sendKeys(file.getAbsolutePath());
        return this;
    }

    @FindBy(xpath = "//a[@href='#tab-information']")
    private WebElement informationTab;

    @FindBy(xpath = "//select[@name='manufacturer_id']")
    private WebElement manufacturerDropdown;

    public CatalogPageObject openInformationTabAndWait() {
        informationTab.click();
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(manufacturerDropdown));
        return this;
    }

    @FindBy(xpath = "//select[@name='manufacturer_id']//option[@value='1']")
    private WebElement acmeCorpManufacturer;

    public CatalogPageObject selectManufacturer() {
        manufacturerDropdown.click();
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(acmeCorpManufacturer));
        acmeCorpManufacturer.click();
        return this;
    }

    @FindBy(xpath = "//input[@name='short_description[en]']")
    private WebElement shortDescriptionInput;

    public CatalogPageObject setShortDescription(String description) {
        shortDescriptionInput.sendKeys(description);
        return this;
    }

    @FindBy(xpath = "//div[@class='trumbowyg-editor']")
    private WebElement descriptionInput;

    public CatalogPageObject setDescription(String description) {
        descriptionInput.sendKeys(description);
        return this;
    }

    @FindBy(xpath = "//a[@href='#tab-prices']")
    private WebElement pricesTab;

    public CatalogPageObject openPricesTabAndWait() {
        pricesTab.click();
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(purchasePriceInput));
        return this;
    }

    @FindBy(xpath = "//input[@name='purchase_price']")
    private WebElement purchasePriceInput;

    public CatalogPageObject setPurchasePrice(String price) {
        purchasePriceInput.sendKeys(Keys.DELETE + price);
        return this;
    }

    @FindBy(xpath = "//input[@name='prices[USD]']")
    private WebElement priceUSDInput;

    public CatalogPageObject setUSDPrice(String price) {
        priceUSDInput.sendKeys(price);
        return this;
    }

    @FindBy(xpath="//button[@name='save']")
    private WebElement saveButton;

    public CatalogPageObject saveNewProduct() {
        saveButton.click();
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(listOfProducts.get(0)));
        return this;
    }

    @FindBy(xpath="//form[@name='catalog_form']//tr[@class='row']")
    private List<WebElement> listOfProducts;

    public CatalogPageObject saveAmountOfProductsAtBegining() {
        this.amountOfProductsBeforeTest = listOfProducts.size();
        return this;
    }

    public void checkThatAmountOfProductsWereIncreased() {
        System.out.println("Amount of products before test was: " + this.amountOfProductsBeforeTest);
        System.out.println("Amount of products after test was: " + listOfProducts.size());
        if (listOfProducts.size() <= this.amountOfProductsBeforeTest) {
            assertTrue("New product was NOT added", false);
        }
    }
}
