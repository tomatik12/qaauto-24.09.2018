import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchPage {

    /**
     * PreConditions:
     * - Open new Browser.
     * - Navigete to linkedin.com
     * <p>
     * Scenario:
     * - Verify that Login page is loaded.
     * - Login with valid credentials.
     * - Verify that Home page is loaded.
     * - Enter 'searchTerm' into search field and press RETURN key.
     * - Verify that Search page is loaded.
     * - Verify 10 searchResults displayed on page.
     * - Verify each result Item contains 'searchTerm'.
     * PostConditions:
     * - Close Browser.
     */

    WebDriver webDriver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {

        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com/");
        loginPage = new LoginPage(webDriver);
    }

    @AfterMethod
    public void afterMethod () {

        webDriver.quit();
    }

    @Test
    public void basicSearchTest() {

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded.");
        HomePage homePage = loginPage.login("melnyktoma.92@gmail.com", "malyavo4ka");
        Assert.assertTrue(homePage.isHomePageLoaded(),"Home Page is not loaded.");

}

