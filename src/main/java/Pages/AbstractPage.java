package Pages;

import Utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AbstractPage {

    protected WebDriver driver;
    protected WebDriverUtils webDriverUtils;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        webDriverUtils = new WebDriverUtils(this.driver);
    }

    public void typeText(By elementId, String text) {
        WebElement textBox = driver.findElement(elementId);
        textBox.clear();
        textBox.sendKeys(text);
    }

    public void click(By elementId) {
        WebElement button = driver.findElement(elementId);
        button.click();
    }

    public String getTextFromElement(By elementId) {
        WebElement element = driver.findElement(elementId);
        return element.getText().trim();
    }
}
