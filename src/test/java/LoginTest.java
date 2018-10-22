import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest {
    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){

        webDriver = new FirefoxDriver();
    }

    @AfterMethod
    public void afterMethod(){

        webDriver.quit();
    }

    /**
     * Preconditions:
     * - Open FF Browser.
     *
     * Scenario:
     * - Navigate to https://www.linkedin.com/.
     * - Verify that Login page is loaded.
     * - Enter userEmail into userEmail field.
     * - Enter userPassword  into userPassword field.
     * - Click on signIn button.
     * - Verify that Home page is loaded.
     *
     * PostCondition:
     * - Close FF browser.
     */
    @Test
    public void successfulLoginTest() {

        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded.");
        loginPage.login("melnyktoma.92@gmail.com", "malyavo4ka");
        HomePage homePage = new HomePage(webDriver);
        Assert.assertTrue(homePage.isHomePageLoaded(),"Home Page is not loaded.");

    }

    @Test

    public void negativeLoginWithEmptyPasswordTest(){

        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded.");
        loginPage.login("a@b.c", "");
        Assert.assertTrue(loginPage.isPageLoaded(),"Login Page is not loaded.");
    }

    @Test

    public void negativeWrongPasswordTest(){

        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded.");
        loginPage.login("melnyktoma.92@gmail.com", "melnyk");
        LoginSubmit loginSubmit = new LoginSubmit(webDriver);
        Assert.assertTrue(loginSubmit.isLoginSubmitLoaded(),"Login Submit is not loaded.");
    }

    @Test

    public void negativeWrongUserEmailTest(){

        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login("melnyktoma.92gmail.com", "malyavo4ka");
        LoginSubmit loginSubmit = new LoginSubmit(webDriver);
        Assert.assertTrue(loginSubmit.isLoginSubmitLoaded(),"Login Submit is not loaded.");

    }

    @Test

    public void negativeLoginWithEmptyUserEmailTest(){
        webDriver.get("https://www.linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login("","malyavo4ka");
        Assert.assertTrue(loginPage.isPageLoaded(),"Login Page is not loaded.");
    }
}


