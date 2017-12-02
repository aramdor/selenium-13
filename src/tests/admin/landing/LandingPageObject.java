package tests.admin.landing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static common.WebDriverUtils.PAGE_LOAD_TIMEOUT_SECONDS;
import static org.testng.AssertJUnit.assertFalse;

public class LandingPageObject {
    protected WebDriver driver;

    public LandingPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = ".//*[@id='box-apps-menu']")
    private WebElement appsMenu;

    public LandingPageObject checkThatItIsLandingPage(Boolean assertIs) {
        try {
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(appsMenu));}
                catch (WebDriverException ex) {
            assertFalse("it is NOT Landing Page!", assertIs);
                }
        return this;
    }
}
