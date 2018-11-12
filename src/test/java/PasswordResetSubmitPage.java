import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class PasswordResetSubmitPage extends BasePage{

    private WebDriver webDriver;
    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    public PasswordResetSubmitPage (WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded(){
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resendLinkButton.isDisplayed();
                //&& webDriver.getTitle().contains("Проверьте свою эл. почту и перейдите по безопасной ссылке.")
                //&& webDriver.getCurrentUrl().contains("request-password-reset-submit");
    }

    public SetNewPasswordPage navigateToLinkFromEmail(){
        String messageSubject = "нажмите здесь или вставьте в адресную строку браузера следующую ссылку";
        String messageTo = "melnyktoma.92@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);
        String resetPasswordLink = StringUtils.substringBetween(
                message, "click <a href=\"", "\"").replace("amp;", "");
        System.out.println(resetPasswordLink);
        webDriver.get(resetPasswordLink);

        System.out.println(resetPasswordLink);
        webDriver.get(resetPasswordLink);

        return new SetNewPasswordPage(webDriver);
    }
}
