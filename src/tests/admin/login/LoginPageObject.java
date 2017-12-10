package tests.admin.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.admin.landing.LandingPageForAdminObject;

import static common.WebDriverUtils.PAGE_LOAD_TIMEOUT_SECONDS;
import static org.testng.AssertJUnit.assertTrue;

public class LoginPageObject {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='box-login']//*[@alt='My Store']")
    private WebElement loginPopupLogo;

    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameTextBox;


    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//button[@name='login']")
    private WebElement loginButton;

    public LoginPageObject getLoginPageAndWaitForLogo() {
        driver.get("http://localhost/litecart/admin/");
        try {
            (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.visibilityOf(loginPopupLogo));
        } catch (WebDriverException ex) {
            System.out.println("It is NOT admin login page. Let's check is it landing page!");
            LandingPageForAdminObject landingPage = new LandingPageForAdminObject(driver);
            landingPage.checkThatItIsLandingPage(true);
            assertTrue("Landing page was opened instead of login pop-up!", false);
        }
        return this;
    }

    public LoginPageObject inputUsername(String username) {
        usernameTextBox.sendKeys(username);
        return this;
    }

    public LoginPageObject inputPassword(String password) {
        passwordTextBox.sendKeys(password);
        return this;
    }

    public LoginPageObject pressLoginButtonAndWaitForLandingPage() {
        loginButton.click();
        LandingPageForAdminObject landingPage = new LandingPageForAdminObject(driver);
        landingPage.checkThatItIsLandingPage(true);
        return this;
    }

    public void loginUnderDefaultAdminAccountAndWaitForLandingPage() {
        getLoginPageAndWaitForLogo()
                .inputUsername("admin")
                .inputPassword("admin")
                .pressLoginButtonAndWaitForLandingPage();
    }

}
