package tests.user.registration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.user.LandingPageObject;

import java.io.File;
import java.io.IOException;

import static common.WebDriverUtils.PAGE_LOAD_TIMEOUT_SECONDS;
import static org.testng.AssertJUnit.assertTrue;

public class CreateAccountPageObject {
    protected WebDriver driver;
    EmailObject emailObject;

    CreateAccountPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.emailObject = new EmailObject();
    }

    @FindBy(xpath = "//header//*[@id='logotype-wrapper']")
    private WebElement headerIcon;

    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@name='address1']")
    private WebElement address;

    @FindBy(xpath = "//input[@name='postcode']")
    private WebElement postcode;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement city;

    @FindBy(xpath = "//*[@class='select2-selection select2-selection--single']")
    private WebElement country;

    @FindBy(xpath = "//*[@class='select2 select2-container select2-container--default select2-container--below select2-container--open']")
    private WebElement openedCountryDropdown;

    @FindBy(xpath = "//input[@class='select2-search__field']")
    private WebElement openedCountryInput;

    @FindBy(xpath = "//li[contains(@id,'US')]")
    private WebElement usaFromDropdown;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@name='phone']")
    private WebElement phone;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@name='confirmed_password']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//button[@name='create_account']")
    private WebElement createAccountButton;


    public CreateAccountPageObject getLandingPageAndWaitForHeader() {
        driver.get("http://localhost/litecart/en/create_account");
        try {
            (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.visibilityOf(headerIcon));
        } catch (WebDriverException ex) {
            assertTrue("Failed to open registration page!", false);
        }
        return this;
    }

    public CreateAccountPageObject inputFirstName(String input) {
        firstName.sendKeys(input);
        return this;
    }

    public CreateAccountPageObject inputLastName(String input) {
        lastName.sendKeys(input);
        return this;
    }

    public CreateAccountPageObject inputAddress(String input) {
        address.sendKeys(input);
        return this;
    }

    public CreateAccountPageObject inputPostcode(String input) {
        postcode.sendKeys(input);
        return this;
    }

    public CreateAccountPageObject inputCity(String input) {
        city.sendKeys(input);
        return this;
    }

    public CreateAccountPageObject inputEmail(String input) {
        email.sendKeys(input);
        return this;
    }

    public CreateAccountPageObject inputPhone(String input) {
        phone.sendKeys(input);
        return this;
    }

    public CreateAccountPageObject inputPassword(String input) {
        this.emailObject.setPassword(input);
        password.sendKeys(input);
        return this;
    }

    public CreateAccountPageObject inputConfirmPassword(String input) {
        confirmPassword.sendKeys(input);
        return this;
    }

    public CreateAccountPageObject selectCountry(String input) {
        country.click();
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(openedCountryInput));
        openedCountryInput.sendKeys(input);
        usaFromDropdown.click();
        return this;
    }

    private String filepath = "src/tests/user/registration/usedEmailsIncrement.json";

    private void writeEmailToFile(int increment) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        EmailObject emailToWrite = new EmailObject();
        emailToWrite.setDefaultEmail(increment);
        mapper.writeValue(new File(filepath), emailToWrite);
    }



    public CreateAccountPageObject inputEmailWithUniqueIncrement() throws IOException {
        EmailObject uniqueEmail = new EmailObject();
        int increment = readIncrementFromFile();
        uniqueEmail.setDefaultEmail(increment);
        this.emailObject = uniqueEmail;
        writeEmailToFile(increment+1);
        inputEmail(uniqueEmail.emailWithIncrement);
        return this;
    }

    private int readIncrementFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filepath);
        JsonNode json = mapper.readTree(file);
        return json.get("increment").asInt();
    }

    public void createAccountAndWait() {
        createAccountButton.click();
        LandingPageObject landingPage = new LandingPageObject(driver);
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(landingPage.logoutButton));
        landingPage.isItLandingPage();
    }
}
