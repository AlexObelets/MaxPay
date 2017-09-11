package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends AbstractPage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }


    public DashboardPage checkPage(String pageTitle) throws Exception {
        webDriverUtils.waitForExpectedCondition(ExpectedConditions.titleContains(pageTitle));
        return this;
    }
}
