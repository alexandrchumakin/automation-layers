package form;

import driver.DriverManager;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import service.StringExtension;

import java.util.Arrays;

@Data
public class PostFormPage {
    @FindBy(name = "custname")
    private WebElement customerName;

    @FindBy(name = "custtel")
    private WebElement customerTel;

    @FindBy(name = "custemail")
    private WebElement customerEmail;

    @FindBy(xpath = "//legend[contains(text(), 'Pizza Size')]/..")
    private WebElement pizzaSize;

    @FindBy(xpath = "//legend[contains(text(), 'Pizza Toppings')]/..")
    private WebElement pizzaToppings;

    @FindBy(name = "delivery")
    private WebElement deliveryTime;

    @FindBy(name = "comments")
    private WebElement deliveryInstructions;

    @FindBy(xpath = "//button[text()='Submit order']")
    private WebElement submitOrder;

    PostFormPage() {
        PageFactory.initElements(DriverManager.getInstance(), this);
    }

    void setPizzaSize(String sizeValue) {
        try {
            pizzaSize.findElement(By.xpath(String.format(".//input[@value='%s']", sizeValue))).click();
        } catch (NoSuchElementException ex) {
            System.out.println(String.format("Cannot set pizza size `%s`: %s", sizeValue, StringExtension.formatMessage(ex)));
        }
    }

    void setPizzaToppings(String... toppings) {
        try {
            for (String topping : toppings) {
                pizzaToppings.findElement(By.xpath(String.format(".//input[@value='%s']", topping))).click();
            }
        } catch (NoSuchElementException ex) {
            System.out.println(String.format("Cannot set pizza toppings `%s`: %s", Arrays.toString(toppings),
                    StringExtension.formatMessage(ex)));
        }
    }

    void clickSubmitOrder() {
        submitOrder.click();
        DriverManager.waitPageSource();
    }

    String getValidationMessage() {
        try {
            WebElement validationMessage = DriverManager.getInstance().findElement(By.cssSelector("input:invalid"));
            return validationMessage.getAttribute("validationMessage");
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Cannot find validation message: " + StringExtension.formatMessage(ex));
        }
    }
}
