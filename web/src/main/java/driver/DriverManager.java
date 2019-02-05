package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import service.StringExtension;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getInstance() {
        if (driver == null) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/lib/geckodriver");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void waitPageSource(){
        WebDriver currentDriver = DriverManager.getInstance();
        try {
            for(int i = 0; i < 20; i++) {
                String currentSource = currentDriver.getPageSource();
                Thread.sleep(200);
                String newSource = currentDriver.getPageSource();
                if (currentSource.equals(newSource))
                    break;
                else
                    Thread.sleep(200);
            }
        }
        catch (InterruptedException ex){
            System.out.println(StringExtension.formatMessage(ex));
        }
    }
}
