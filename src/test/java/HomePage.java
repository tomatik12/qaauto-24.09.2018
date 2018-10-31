import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
   private  WebDriver webDriver;

   @FindBy(xpath = "//li[@id='profile-nav-item']")
   private WebElement profileNavItem;


    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

     public boolean isHomePageLoaded () {
         return webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                 && webDriver.getTitle().equals("LinkedIn")
                 && isProfileNavItemDisplayed();
     }

    public boolean isProfileNavItemDisplayed(){return profileNavItem.isDisplayed();}




}
