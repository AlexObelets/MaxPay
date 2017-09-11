package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class LogInPage extends AbstractPage {

    private final static By EMAIL_FIELD = By.id("login-email"),
            PASSWORD_FIELD = By.id("login-password"),
            MASSAGE_FIELD = By.cssSelector("div[data-ng-show=\"errorMsg\"] p");


    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public LogInPage open(String url) {
        driver.get(url);
        return this;
    }

    public LogInPage checkPage(String pageTitle) throws Exception {
        webDriverUtils.waitForExpectedCondition(ExpectedConditions.titleContains(pageTitle));
        return this;
    }

    public LogInPage loginAsUser(String email, String password) {
        typeText(EMAIL_FIELD, email);
        typeText(PASSWORD_FIELD, password);
        driver.findElement(PASSWORD_FIELD).submit();
        return this;
    }

    public LogInPage checkMessage(String expectedResult) throws Exception {
        webDriverUtils.waitForExpectedCondition(ExpectedConditions.visibilityOfElementLocated(MASSAGE_FIELD));
        Assert.assertTrue(getTextFromElement(MASSAGE_FIELD).contains(expectedResult));
        return this;
    }
}
