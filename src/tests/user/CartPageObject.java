package tests.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class CartPageObject {
    protected WebDriver driver;
    private int amountOfItemsInSummaryTable;

    public CartPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath="//div[@id='order_confirmation-wrapper']")
    public WebElement orderSummaryTable;

    @FindBy(xpath="//li[@class='shortcut']")
    public List<WebElement> listOfDifferentItemsInCart;

    @FindBy(xpath="//button[@name='remove_cart_item']")
    private WebElement removeFromCartButton;

    public CartPageObject removeFromCart() {
        removeFromCartButton.click();
        return this;
    }

    @FindBy(xpath="//div[@id='order_confirmation-wrapper']//td[@class='item']")
    private List<WebElement> listOfItemsInSummaryTable;

    public CartPageObject writeAmountOfItemsInSummaryTable() {
        this.amountOfItemsInSummaryTable = listOfItemsInSummaryTable.size();
        return this;
    }

    public CartPageObject isAmountOfItemsInSummaryTableDecreased() throws InterruptedException {
        for (int counter = 0; counter <= 5; counter++) {
            if (listOfItemsInSummaryTable.size() < this.amountOfItemsInSummaryTable)
            {
                break;
            }
            Thread.sleep(500);
        }
        if (listOfItemsInSummaryTable.size() >= this.amountOfItemsInSummaryTable)
        {
            assertTrue("Amount of items in cart was NOT decreased", false);
        }
        this.amountOfItemsInSummaryTable = listOfItemsInSummaryTable.size();
        return this;
    }

    @FindBy(xpath="//p/em")
    private WebElement noItemsInCart;

    public void checkThatCartIsEmpty() {
        assertEquals("Cart is NOT empty!", "There are no items in your cart.", noItemsInCart.getText().trim());
    }
}
