import org.testng.Assert;
import org.testng.annotations.Test;


import static java.lang.Thread.sleep;

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

public class ResetPasswordTest extends BaseTest{
    @Test

    public void successfulResetPasswordTest() throws InterruptedException {
    String newPassword = "malyavo4ka12";

    Assert.assertTrue(loginPage.isPageLoaded(),
            "LoginPage is not loaded.");

    RequestPasswordResetPage requestPasswordResetPage =
           loginPage.clickOnForgotPasswordLink();
    Assert.assertTrue(requestPasswordResetPage.isPageLoaded(),
            "page.RequestPasswordResetPage is not loaded.");

    PasswordResetSubmitPage passwordResetSubmitPage =
            requestPasswordResetPage.findAccount("melnyktoma.92@gmail.com");


    sleep(180000);
    Assert.assertTrue(passwordResetSubmitPage.isPageLoaded(),
            "page.PasswordResetSubmitPage is not loaded.");

SetNewPasswordPage setNewPasswordPage =
            passwordResetSubmitPage.navigateToLinkFromEmail();
    Assert.assertTrue(setNewPasswordPage.isLoaded(),
            "page.SetNewPasswordPage is not loaded.");

SuccessfulPasswordResetPage successfulPasswordResetPage =
           setNewPasswordPage.submitNewPassword(newPassword);

    Assert.assertTrue(successfulPasswordResetPage.isLoaded(),
            "page.SuccessfulPasswordResetPage is not loaded.");

    HomePage homePage =
            successfulPasswordResetPage.clickOnGoToHomeButton();
//sleep(180000);

    Assert.assertTrue(homePage.isHomePageLoaded(),
            "page.HomePage is not loaded.");
}
}
