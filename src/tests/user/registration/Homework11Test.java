package tests.user.registration;

import common.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.user.LandingPageObject;

import java.io.IOException;

public class Homework11Test {
    private WebDriver driver;
    private CreateAccountPageObject createAccountPage;
    private LandingPageObject landingPage;

    @BeforeMethod
    public void setup() {
        driver = WebDriverUtils.launchChromeDriver();
//        driver = WebDriverUtils.launchFirefoxDriver();
//        driver = WebDriverUtils.launchEdgeDriver();
        createAccountPage = new CreateAccountPageObject(driver);
        landingPage = new LandingPageObject(driver);
    }

    @Test
    public void createAccount() throws IOException {
        createAccountPage
                .getLandingPageAndWaitForHeader()
                .inputFirstName("Iaroslav")
                .inputLastName("Stepanov")
                .inputAddress("address")
                .inputPostcode("99999")
                .inputCity("South Park")
                .selectCountry("United States")
                .inputPhone("911")
                .inputEmailWithUniqueIncrement()
                .inputPassword("password")
                .inputConfirmPassword("password")
                .createAccountAndWait();
        landingPage
                .logoutAndWait();
        landingPage
                .inputEmailAndPasswordToLoginForm(createAccountPage.emailObject)
                .pressLoginButtonAndWait();
        landingPage
                .logoutAndWait();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
