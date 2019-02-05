package driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.StringExtension;

public class WebDriverHelper {
    public static WebElement findElement(By by) {
        WebDriver driver = DriverManager.getInstance();
        WebDriverWait waiter = new WebDriverWait(driver, 5);
        WebElement element = null;
        try {
            waiter.until(ExpectedConditions.presenceOfElementLocated(by));
            element = driver.findElement(by);
            scrollToElement(driver, element);    // try to make focus
        } catch (Exception ex) {
            System.out.println(String.format("\r\nDEBUG: Cannot set focus to element with '%1$s' locator, error: '%2$s'",
                    by, StringExtension.formatMessage(ex)));
        }
        return element;
    }

    private static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void clickWithJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
