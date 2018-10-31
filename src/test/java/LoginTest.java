import org.openqa.selenium.WebDriver;
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
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"melnyktoma.92@gmail.com", "malyavo4ka"},
                {"melnyktoma@gmail.com", "malyavo4ka"},
                {" melnyktoma.92@gmail.com ", " malyavo4ka "}

        };
    }
    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
                { "melnyktoma.92@gmail.com", "wrong", "", "Это неверный пароль. Повторите попытку или измените пароль."},
                { "melnyktoma.92@gmail.com", "123", "", "Пароль должен содержать не менее 6 символов."},
                { "melnyktoma@gmail.com", "malyavo4ka", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},
                { "mel", "malyavo4ka", "Введите действительное имя пользователя", ""}
        };
    }

    @DataProvider
    public Object[][] emptyFieldsDataProvider() {
        return new Object[][]{
                { "", ""},
                { "melnyktoma.92@gmail.com", ""},
                { "", "malyavo4ka"}
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

    public void errorMessagesOnInvalidEmailPasswordTest
            (String userEmail,
             String userPassword,
             String emailValidationMessage,
             String passwordValidationMessage){


        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded.");
        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginSubmitPage.isErrorPageLoaded(), "Login Submit page is not loaded");

        Assert.assertEquals(loginSubmitPage.getAlertMessageText(),
                "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Alert message text is wrong");

        Assert.assertEquals(loginSubmitPage.getEmailValidationMessage(),
                emailValidationMessage, "Email validation message is wrong");
        Assert.assertEquals(loginSubmitPage.getPasswordValidationMessage(),
                passwordValidationMessage, "Password validation message is wrong");
    }


    @Test(dataProvider = "emptyFieldsDataProvider")
    public void emptyFieldsLoginTest(String userEmail, String userPassword){
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginPage loginPage1 = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginPage1.isPageLoaded(), "Login Submit page is not loaded");
    }
}



