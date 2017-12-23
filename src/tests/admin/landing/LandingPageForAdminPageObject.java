package tests.admin.landing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static common.WebDriverUtils.PAGE_LOAD_TIMEOUT_SECONDS;
import static org.testng.AssertJUnit.*;


public class LandingPageForAdminPageObject {
    protected WebDriver driver;

    public LandingPageForAdminPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='box-apps-menu']")
    private WebElement appsMenu;

    @FindBy(xpath = "//*[@id='content']")
    private WebElement mainPage;

    @FindBy(xpath = "//form[@name='countries_form']")
    public static WebElement countriesForm;

    @FindBy(xpath = "//form[@name='geo_zones_form']")
    public static WebElement geoZonesForm;

    @FindBy(xpath = "//form[@name='countries_form']//tr[@class='row']")
    private List<WebElement> countiesRows;

    @FindBy(xpath = "//form[@method='post']")
    private WebElement editForm;

    private String countryNameXPATH = "./td[5]";
    private String countryNameLinkToEditXPATH = countryNameXPATH + "/a";
    private String countryAmountOfZonesXPATH = "./td[6]";

    @FindBy(xpath = "//table[@id='table-zones']")
    private WebElement zonesFullTable;

    private String zonesNames = ".//tr/td[contains(@name,'[name]')]";

    private String zonesNamesGZ = ".//select[contains(@name,'[zone_code]')]//option[@selected = 'selected']";

    public LandingPageForAdminPageObject checkThatItIsLandingPage(Boolean assertIs) {
        try {
            (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                    .until(ExpectedConditions.visibilityOf(appsMenu));
        } catch (WebDriverException ex) {
            assertFalse("it is NOT Landing Page!", assertIs);
        }
        return this;
    }

    public WebElement getMainPageHeader() {
        return mainPage.findElement(By.xpath(".//h1"));
    }

    public LandingPageForAdminPageObject isHeaderDisplayed() {
        assertTrue("Landing page header is NOT displayed!", getMainPageHeader().isDisplayed());
        return this;
    }

    public List<String> getListOfCountriesNames() {
        List<String> actualCountries = new ArrayList<>();
        for (int countryId = 0; countryId < countiesRows.size(); countryId++) {
            actualCountries.add(countiesRows.get(countryId).findElement(By.xpath(countryNameXPATH)).getText());
        }
        return actualCountries;
    }

    public LandingPageForAdminPageObject checkCountryNamesSorting() {
        List<String> actualListOfCountries = getListOfCountriesNames();
        Object[] sortedCountries = actualListOfCountries.toArray();
        Arrays.sort(sortedCountries);
        for (int countryId = 0; countryId < actualListOfCountries.size(); countryId++) {
            assertEquals("Incorrect sorting! User could see: [" + actualListOfCountries.get(countryId) + "] instead of: [" + sortedCountries[countryId] + "]", sortedCountries[countryId], actualListOfCountries.get(countryId));
        }
        return this;
    }

    public List<WebElement> getCountriesWithZones() {
        List<WebElement> countriesWithZones = new ArrayList<>();
        for (int countryId = 0; countryId < countiesRows.size(); countryId++) {
            if (!Objects.equals(countiesRows.get(countryId).findElement(By.xpath(countryAmountOfZonesXPATH)).getText(), "0")) {
                countriesWithZones.add(countiesRows.get(countryId));
            }
        }
        return countriesWithZones;
    }

    public void openCountryForEditAndWait(WebElement country) {
        country.findElement(By.xpath(countryNameLinkToEditXPATH)).click();
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(editForm));
    }

    public void checkZonesSorting() {
        List<String> actualListOfZonesNames = getListOfZonesNames();
        Object[] sortedZonesNames = actualListOfZonesNames.toArray();
        Arrays.sort(sortedZonesNames);
        for (int countryId = 0; countryId < actualListOfZonesNames.size(); countryId++) {
            assertEquals("Incorrect sorting! User could see: [" + actualListOfZonesNames.get(countryId) + "] instead of: [" + sortedZonesNames[countryId] + "]", sortedZonesNames[countryId], actualListOfZonesNames.get(countryId));
        }
    }

    private List<String> getListOfZonesNames() {
        List<String> actualZonesNames = new ArrayList<>();
        List<WebElement> zonesTablesRows = zonesFullTable.findElements(By.xpath(zonesNames));
        for (int countryId = 0; countryId < zonesTablesRows.size(); countryId++) {
            actualZonesNames.add(zonesTablesRows.get(countryId).getText());
        }
        return actualZonesNames;
    }


    public List<WebElement> getListOfCountiresForGZ() {
        return geoZonesForm.findElements(By.xpath(".//tr[@class='row']//a[@title='Edit']"));
    }

    public void openGZCountryForEditAndWait(WebElement country) {
        country.click();
        (new WebDriverWait(driver, PAGE_LOAD_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(zonesFullTable));
    }

    public void checkZonesSortingForGZ() {
        List<String> actualListOfZonesNames = getListOfZonesNamesForGZ();
        Object[] sortedZonesNames = actualListOfZonesNames.toArray();
        Arrays.sort(sortedZonesNames);
        for (int countryId = 0; countryId < actualListOfZonesNames.size(); countryId++) {
            assertEquals("Incorrect sorting! User could see: [" + actualListOfZonesNames.get(countryId) + "] instead of: [" + sortedZonesNames[countryId] + "]", sortedZonesNames[countryId], actualListOfZonesNames.get(countryId));
        }
    }

    private List<String> getListOfZonesNamesForGZ() {
        List<String> actualZonesNames = new ArrayList<>();
        List<WebElement> zonesTablesRows = zonesFullTable.findElements(By.xpath(zonesNamesGZ));
        for (int countryId = 0; countryId < zonesTablesRows.size(); countryId++) {
            actualZonesNames.add(zonesTablesRows.get(countryId).getText());
            System.out.println("id: [" + countryId + "] text: [" + zonesTablesRows.get(countryId).getText() + "]");
        }
        return actualZonesNames;
    }
}
