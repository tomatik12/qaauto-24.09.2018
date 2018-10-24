import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmit {
    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id = 'control_gen_1']")
    private WebElement errorMessage;

    @FindBy(xpath ="//*[@id='error-for-username']" )
    private WebElement loginError;

    @FindBy(xpath = "//*[@id='error-for-password']")
    private WebElement passwordError;


 public LoginSubmit(WebDriver webDriver){
     this.webDriver = webDriver;
     PageFactory.initElements(webDriver, this);
 }

 public boolean isErrorPageLoaded(){

     return webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
             && webDriver.getTitle().equals("Войти в LinkedIn")
             && isErrorMessageDisplayed();
 }
    public boolean isErrorMessageDisplayed(){
        return errorMessage.isDisplayed();
    }


}
