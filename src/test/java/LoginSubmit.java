import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginSubmit {
    private WebDriver webDriver;
    private WebElement alert;

 public LoginSubmit(WebDriver webDriver){
     this.webDriver = webDriver;
     initElements();
 }

 public boolean isLoginSubmitLoaded(){
     return webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
             && webDriver.getTitle().equals("Войти в LinkedIn")
             && isAlertDisplayed();
 }

 public boolean isAlertDisplayed(){ return alert.isDisplayed();}

    private void initElements(){
    alert = webDriver.findElement(By.xpath("//div[@role='alert']"));
}


}
