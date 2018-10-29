import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmit {
    private WebDriver webDriver;

    @FindBy (xpath = "//span[@id = 'session_password-login-error']")
    private WebElement passwordValidationMessage;

    @FindBy(xpath = "//span[@id = 'session_key-login-error']")
    private WebElement emailValidationMessage;

    @FindBy(xpath = "//div[@role = 'alert']")
    private WebElement alertBox;


    public LoginSubmit (WebDriver webDriver){
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isErrorPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
                && webDriver.getTitle().contains("Войти в LinkedIn")
                && isErrorMessageDisplayed();
    }

    public boolean isErrorMessageDisplayed(){
        return alertBox.isDisplayed();
    }

    public String getAlertMessageText() {
       return alertBox.getText();
    }

    public String getEmailValidationMessage() {
        return emailValidationMessage.getText();
    }

    public String getPasswordValidationMessage() {
        return passwordValidationMessage.getText();
    }
}