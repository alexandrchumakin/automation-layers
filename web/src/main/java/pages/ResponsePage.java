package pages;

import driver.DriverManager;
import driver.WebDriverHelper;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class ResponsePage {
    @FindBy(tagName = "pre")
    private WebElement responseJson;

    @FindBy(id = "rawdata-tab")
    private WebElement viewRawData;

    public ResponsePage() {
        PageFactory.initElements(DriverManager.getInstance(), this);
    }

//    public WebElement getResponseJson() {
//        return WebDriverHelper.findElement(By.tagName("pre"));
//    }
}
