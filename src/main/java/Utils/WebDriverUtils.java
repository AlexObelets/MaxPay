package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {

    WebDriverWait wait;

    public WebDriverUtils(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 20);
    }

    public void waitForExpectedCondition(ExpectedCondition expectedCondition) {
        wait.until(expectedCondition);
    }
}
