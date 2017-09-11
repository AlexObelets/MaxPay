import Pages.DashboardPage;
import Pages.LogInPage;
import core.TestBase;
import org.testng.annotations.Test;

public class UiTest extends TestBase {

    @Test(priority = 1)
    public void LogInPositive() throws Exception {
        LogInPage logIn = new LogInPage(driver);
        logIn
                .open("https://my-sandbox.maxpay.com/#/signin")
                .checkPage("Login page — Merchant portal")
                .loginAsUser("test_user@test.com", "Qwerty123");
        DashboardPage dashboard = new DashboardPage(driver);
        dashboard
                .checkPage("Dashboard — Merchant portal");
    }

    @Test(priority = 2)
    public void LogInNegative() throws Exception {
        LogInPage login = new LogInPage(driver);
        login
                .open("https://my-sandbox.maxpay.com/#/signin")
                .checkPage("Login page — Merchant portal")
                .loginAsUser("t_user@test.com", "1111")
                .checkMessage("Некорректны пароль или email");
    }
}
