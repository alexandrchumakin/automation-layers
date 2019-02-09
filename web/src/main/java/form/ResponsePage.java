package form;

import driver.DriverManager;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
class ResponsePage {
    @FindBy(tagName = "pre")
    private WebElement responseJson;

    @FindBy(id = "rawdata-tab")
    private WebElement viewRawData;

    ResponsePage() {
        PageFactory.initElements(DriverManager.getInstance(), this);
    }
}
