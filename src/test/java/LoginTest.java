import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;


public class LoginTest {
    WebDriver webDriver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {

        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com/");
        loginPage = new LoginPage(webDriver);
    }

    @AfterMethod
    public void afterMethod() {

        webDriver.quit();
    }

    @DataProvider
    public Object[][] ValidDataProvider() {
        return new Object[][]{
                {"melnyktoma.92@gmail.com", "malyavo4ka"},
                {"melnyktoma@gmail.com", "malyavo4ka"},
                {" melnyktoma.92@gmail.com ", " malyavo4ka "}

        };
    }

    @DataProvider
    public Object[][] validationMessagesCombinations() {
        return new Object[][]{
                {"melnyktoma.92@gmail.com",
                        "wrong", "Это неверный пароль. Повторите попытку или измените пароль",
                        "", ""}
        };
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
    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded.");
        HomePage homePage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isHomePageLoaded(),"Home Page is not loaded.");

    }

    @Test(dataProvider = "invalidDataProvider")

    public void negativeLoginWithEmptyPasswordTest(String userEmail,
                                                   String userPassword, String emailValidationMessage,
                                                   String passwordValidationMessage){


        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded.");
        LoginPage loginPage = loginPage.login(userEmail, userPassword);
        try {
            sleep(5000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        Assert.assertTrue(loginPage.isPageLoaded(),"Login Page is not loaded.");
    }

    @Test

    public void errorMessagesOnInvalidEmailPasswordTest(String userEmail, String userPassword,
                                                        String emailValidationMessage,
                                                        String passwordValidationMessage ){

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded.");
       LoginSubmit loginSubmit = loginPage.login(userEmail, userPassword);
       Assert.assertTrue(loginSubmit.isErrorPageLoaded(), "LoginSubmit page is not loaded.");
       Assert.assertEquals(loginSubmit.getAlertMessageText(),
               "Это неверный пароль. Повторите попытку или измените пароль",
               "Alert message text is not loaded.");

       Assert.assertEquals(loginSubmit.getEmailValidationMessage(),
               emailValidationMessage,
               "Email validation message is wrong.");
       Assert.assertEquals(loginSubmit.getPasswordValidationMessage(), passwordValidationMessage,
               "Password validation message is wrong.");
    }

    @Test

    public void negativeUserEmailWithoutAtTest(){

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        LoginSubmit emailWithoutAt = loginPage.login("melnyktoma.92gmail.com", "malyavo4ka");
        LoginSubmit loginSubmit = new LoginSubmit(webDriver);
        Assert.assertTrue(loginSubmit.isErrorPageLoaded(),"Login Submit Page is not loaded.");

    }

    @Test

    public void negativeLoginWithEmptyUserEmailTest(){

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        LoginPage loginPageWithEmptyEmail = loginPage.login("","malyavo4ka");
        Assert.assertTrue(loginPage.isPageLoaded(),"Login Page is not loaded.");
    }

    @Test

    public void negativeLoginWithEmptyUserEmailAndEmptyPasswordTest(){

        Assert.assertTrue(loginPage.isPageLoaded(),"Login Page is not loaded");
        LoginPage emptyFields = loginPage.login("","");
        Assert.assertTrue(loginPage.isPageLoaded(),"Login Page is not loaded");
    }

    @Test

    public void negativeWrongUserEmailTest(){

        Assert.assertTrue(loginPage.isPageLoaded(),"Login Page is not loaded");
        LoginSubmit wrongEmail = loginPage.login("melnyk@gmail.com", "malyavo4ka");
        LoginSubmit loginSubmit = new LoginSubmit(webDriver);
        Assert.assertTrue(loginSubmit.isErrorPageLoaded(),"Login Submit is not loaded.");

    }
}


