import com.sun.xml.internal.ws.util.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class PasswordResetSubmitPage {

    private final WebDriver webDriver;
    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    public PasswordResetSubmitPage (WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isLoaded(){
        sleep(5000);
        return resendLinkButton.isDisplayed()
                && webDriver.getTitle().contains("Проверьте свою эл. почту и перейдите по безопасной ссылке.")
                && webDriver.getCurrentUrl().contains("request-password-reset-submit");
    }

    public SetNewPasswordPage navigateToLinkFromEmail(){
        String messageSubject = "нажмите здесь или вставьте в адресную строку браузера следующую ссылку";
        String messageTo = "melnyktoma.92@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        Object gMailService;
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);
        String resetPasswordLink =
                StringUtils.substringBetween(message,
                        "To change your LinkedIn password, click <a href=\"",
                        "\" style").replace("amp;","");

        System.out.println(resetPasswordLink);
        webDriver.get(resetPasswordLink);

        return new SetNewPasswordPage(webDriver);
    }
}
