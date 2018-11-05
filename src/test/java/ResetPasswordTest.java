import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class ResetPasswordTest {
    private WebDriver webDriver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://linkedin.com");
        loginPage = new LoginPage(webDriver);
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }
}

/**
 * PreConditions:
 * - Open new Browser.
 * - Navigate to linkedin.com
 * <p>
 * Scenario:
 * - Verify that Login page is loaded.
 * - Verify that "Forget password" button is loaded.
 * - Verify that "Email address or mobile number" field is loaded.
 * - Enter 'valid data' into "Email address or mobile number" field and press RETURN key.
 * - Verify that Email notification page is loaded.
 * -
 * -
 * PostConditions:
 * - Close Browser.
 */

@Test
public void successfulResetPasswordTest() throws InterruptedException {
    String newPassword = "malyavo4ka12";

    Assert.assertTrue(loginPage.isPageLoaded(),
            "LoginPage is not loaded.");

    RequestPasswordResetPage requestPasswordResetPage =
           loginPage.clickOnForgotPasswordLink();
    Assert.assertTrue(requestPasswordResetPage.isLoaded(),
            "RequestPasswordResetPage is not loaded.");

    PasswordResetSubmitPage linkedinPasswordResetSubmitPage =
            RequestPasswordResetPage.findAccount("melnyktoma.92@gmail.com");


    sleep(180000);
    Assert.assertTrue(PasswordResetSubmitPage.isLoaded(),
            "PasswordResetSubmitPage is not loaded.");

SetNewPasswordPage setNewPasswordPage =
            linkedinPasswordResetSubmitPage.navigateToLinkFromEmail();
    Assert.assertTrue(SetNewPasswordPage.isLoaded(),
            "SetNewPasswordPage is not loaded.");

SuccessfulPasswordResetPage successfulPasswordResetPage =
           setNewPasswordPage.submitNewPassword(newPassword);

    Assert.assertTrue(SuccessfulPasswordResetPage.isLoaded(),
            "SuccessfulPasswordResetPage is not loaded.");

    HomePage homePage =
            successfulPasswordResetPage.clickOnGoToHomeButton();

    Assert.assertTrue(homePage.isHomePageLoaded(),
            "HomePage is not loaded.");


}





}
}