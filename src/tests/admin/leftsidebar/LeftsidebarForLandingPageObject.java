package tests.admin.leftsidebar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.admin.landing.LandingPageObject;

import static org.testng.AssertJUnit.*;

import java.util.List;

public class LeftsidebarForLandingPageObject {
    private WebDriver driver;

    public LeftsidebarForLandingPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//li[@id='app-']")
    private List<WebElement> sidebarElements;

    private List<WebElement> getListOfNestedItems(int sidebarElementId) {
        return sidebarElements.get(sidebarElementId).findElements(By.xpath(".//li[@id]"));
    }

    private void checkThatElementIsActive(String actuallClass, int elementId) {
        assertEquals("Element " + elementId + " is NOT active!!", "selected", actuallClass);
    }

    private void checkThatElementIsActive(String actuallClass, int elementId, int nestedElementId) {
        assertEquals("Nested element " + nestedElementId + " of element " + elementId + " is NOT active!!", "selected", actuallClass);
    }

    public LeftsidebarForLandingPageObject clickThroughAllSidebarElements() {
        LandingPageObject landingPage = new LandingPageObject(driver);
        for (int sidebarElementId = 0; sidebarElementId < sidebarElements.size(); sidebarElementId++) {
            sidebarElements.get(sidebarElementId).click();
            checkThatElementIsActive(sidebarElements.get(sidebarElementId).getAttribute("class"), sidebarElementId);
            if (sidebarElements.get(sidebarElementId).getAttribute("outerHTML").contains("<li id=\"doc-")) {
                checkThatElementIsActive(getListOfNestedItems(sidebarElementId).get(0).getAttribute("class"), sidebarElementId, 0);
                landingPage.isHeaderDisplayed();
                for (int nestedElementId = 0; nestedElementId < getListOfNestedItems(sidebarElementId).size(); nestedElementId++) {
                    getListOfNestedItems(sidebarElementId).get(nestedElementId).click();
                    checkThatElementIsActive(getListOfNestedItems(sidebarElementId).get(nestedElementId).getAttribute("class"), sidebarElementId, nestedElementId);
                    landingPage.isHeaderDisplayed();
                    System.out.println(landingPage.getMainPageHeader().getText());
                }
            } else {
                landingPage.isHeaderDisplayed();
                System.out.println(landingPage.getMainPageHeader().getText());
            }
        }
        return this;
    }
}
