package tests.admin.landing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static common.WebDriverUtils.PAGE_LOAD_TIMEOUT_SECONDS;
import static org.testng.AssertJUnit.*;


public class LandingPageObject {
    protected WebDriver driver;

    public LandingPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='box-apps-menu']")
    private WebElement appsMenu;

    @FindBy(xpath = "//*[@id='content']")
    public WebElement mainPage;

    public LandingPageObject checkThatItIsLandingPage(Boolean assertIs) {
        try {
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(appsMenu));}
                catch (WebDriverException ex) {
            assertFalse("it is NOT Landing Page!", assertIs);
                }
        return this;
    }

    public WebElement getMainPageHeader() {
        return mainPage.findElement(By.xpath(".//h1"));
    }

    public LandingPageObject isHeaderDisplayed() {
        assertTrue("Landing page header is NOT displayed!", getMainPageHeader().isDisplayed());
        return this;
    }
}
